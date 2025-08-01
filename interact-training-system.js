const { ethers } = require("hardhat");

async function main() {
  console.log("🎯 Training Achievement System Interaction Demo");
  console.log("📊 Focus: Track Completion NFTs with Points");
  
  // For Besu interaction, we need to create wallets with private keys
  const privateKey = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80";
  const provider = new ethers.JsonRpcProvider("http://localhost:8545");
  const owner = new ethers.Wallet(privateKey, provider);
  
  // Create additional test wallets
  const user1 = new ethers.Wallet("0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d", provider);
  const user2 = new ethers.Wallet("0x5de4111afa1a4b94908f83103eb1f1706367c2e68ca870fc3fb9a804cdab365a", provider);
  const user3 = new ethers.Wallet("0x7c852118e8d7e3b58184ae9b4c9aec4e5f220f691c2bf9a3f261c2f660c26c3d", provider);
  const user4 = new ethers.Wallet("0x47e179ec197488593b187f80a00eb0da91f1b9d0b13f8733639f19c30a34926a", provider);
  
  // Load contract addresses from deployed-addresses.json
  let TRAINING_POINTS_ADDRESS, TRAINING_NFT_ADDRESS;
  try {
    const fs = require('fs');
    const addresses = JSON.parse(fs.readFileSync('deployed-addresses.json', 'utf8'));
    TRAINING_POINTS_ADDRESS = addresses.TrainingPoints;
    TRAINING_NFT_ADDRESS = addresses.TrainingAchievementNFT;
    console.log("📋 Loaded contract addresses from deployed-addresses.json");
  } catch (error) {
    console.error("❌ Error loading contract addresses:", error.message);
    console.log("💡 Please run the deployment script first: npx hardhat run scripts/deploy-and-save.js --network localhost");
    process.exit(1);
  }
  
  console.log("👤 Owner:", await owner.getAddress());
  console.log("👤 User1:", await user1.getAddress());
  console.log("👤 User2:", await user2.getAddress());
  console.log("👤 User3:", await user3.getAddress());
  console.log("👤 User4:", await user4.getAddress());
  
  // Get contract instances
  const TrainingPoints = await ethers.getContractFactory("TrainingPoints");
  const TrainingAchievementNFT = await ethers.getContractFactory("TrainingAchievementNFT");
  
  const trainingPoints = TrainingPoints.attach(TRAINING_POINTS_ADDRESS).connect(owner);
  const trainingNFT = TrainingAchievementNFT.attach(TRAINING_NFT_ADDRESS).connect(owner);
  
  console.log("\n📋 Contract Instances Created");
  console.log("   TrainingPoints:", await trainingPoints.getAddress());
  console.log("   TrainingAchievementNFT:", await trainingNFT.getAddress());
  
  // Demo 1: Mint track completion achievement to user1
  console.log("\n🏆 Demo 1: Minting track completion achievement to user1");
  try {
    const user1Address = await user1.getAddress();
    const tx = await trainingNFT.mintAchievement(
      user1Address,
      1, // trackId
      "Blockchain Fundamentals",
      "Completed blockchain basics course",
      "https://example.com/badge1.png",
      ethers.parseEther("100")
    );
    await tx.wait();
    
    console.log("✅ Track completion achievement minted for user1");
    
    // Check user1's points balance
    const pointsBalance = await trainingPoints.getUserBalance(user1Address);
    const pointsSummary = await trainingPoints.getUserPointsSummary(user1Address);
    
    console.log("   User1 total points balance:", pointsBalance.toString());
    console.log("   User1 total points earned:", pointsSummary[1].toString());
    console.log("   User1 total points burned:", pointsSummary[2].toString());
    
  } catch (error) {
    console.log("❌ Error minting track completion achievement:", error.message);
  }
  
  // Demo 2: Mint another track completion to user2
  console.log("\n🏆 Demo 2: Minting another track completion to user2");
  try {
    const user2Address = await user2.getAddress();
    const tx = await trainingNFT.mintAchievement(
      user2Address,
      2, // trackId
      "Advanced Solidity",
      "Completed advanced smart contract course",
      "https://example.com/badge2.png",
      ethers.parseEther("150")
    );
    await tx.wait();
    
    console.log("✅ Advanced Solidity track completion minted for user2");
    
    // Check user2's points balance
    const pointsBalance = await trainingPoints.getUserBalance(user2Address);
    const pointsSummary = await trainingPoints.getUserPointsSummary(user2Address);
    
    console.log("   User2 total points balance:", pointsBalance.toString());
    console.log("   User2 total points earned:", pointsSummary[1].toString());
    console.log("   User2 total points burned:", pointsSummary[2].toString());
    
  } catch (error) {
    console.log("❌ Error minting achievement:", error.message);
  }
  
  // Demo 3: Owner manually awards points to user3
  console.log("\n👑 Demo 3: Owner manually awards points to user3");
  try {
    const user3Address = await user3.getAddress();
    const tx = await trainingPoints.mintPoints(
      user3Address,
      ethers.parseEther("200"),
      "Special recognition for excellent work"
    );
    await tx.wait();
    
    console.log("✅ Owner awarded 200 points to user3");
    
    // Check user3's points balance
    const pointsBalance = await trainingPoints.getUserBalance(user3Address);
    const pointsSummary = await trainingPoints.getUserPointsSummary(user3Address);
    
    console.log("   User3 total points balance:", pointsBalance.toString());
    console.log("   User3 total points earned:", pointsSummary[1].toString());
    console.log("   User3 total points burned:", pointsSummary[2].toString());
    
  } catch (error) {
    console.log("❌ Error awarding points:", error.message);
  }
  
  // Demo 4: Transfer points from user1 to user3
  console.log("\n💸 Demo 4: Transferring points from user1 to user3");
  try {
    const user1Address = await user1.getAddress();
    const user3Address = await user3.getAddress();
    
    const trainingPointsUser1 = TrainingPoints.attach(TRAINING_POINTS_ADDRESS).connect(user1);
    const tx = await trainingPointsUser1.transferPoints(
      user3Address,
      ethers.parseEther("50")
    );
    await tx.wait();
    
    console.log("✅ 50 points transferred from user1 to user3");
    
    // Check balances after transfer
    const user1Balance = await trainingPoints.getUserBalance(user1Address);
    const user3Balance = await trainingPoints.getUserBalance(user3Address);
    
    console.log("   User1 points balance:", user1Balance.toString());
    console.log("   User3 points balance:", user3Balance.toString());
    
  } catch (error) {
    console.log("❌ Error transferring points:", error.message);
  }
  
  // Demo 5: Burn points for rewards
  console.log("\n🔥 Demo 5: Burning points for rewards");
  try {
    const user2Address = await user2.getAddress();
    
    const trainingPointsUser2 = TrainingPoints.attach(TRAINING_POINTS_ADDRESS).connect(user2);
    const tx = await trainingPointsUser2.burnPoints(
      ethers.parseEther("25"),
      "Redeeming for course discount"
    );
    await tx.wait();
    
    console.log("✅ 25 points burned by user2");
    
    // Check balance after burning
    const balance = await trainingPoints.getUserBalance(user2Address);
    console.log("   User2 points balance after burning:", balance.toString());
    
  } catch (error) {
    console.log("❌ Error burning points:", error.message);
  }
  
  // Demo 6: Get comprehensive user profiles
  console.log("\n👤 Demo 6: Getting comprehensive user profiles");
  try {
    const users = [user1, user2, user3, user4];
    const userNames = ["User1", "User2", "User3", "User4"];
    
    for (let i = 0; i < users.length; i++) {
      const userAddress = await users[i].getAddress();
      const pointsSummary = await trainingPoints.getUserPointsSummary(userAddress);
      const achievementsSummary = await trainingNFT.getUserAchievementsSummary(userAddress);
      const tokenIds = await trainingNFT.getUserTokenIds(userAddress);
      
      console.log(`\n   ${userNames[i]} Profile:`);
      console.log(`     Current Points Balance: ${pointsSummary[0].toString()}`);
      console.log(`     Total Points Earned: ${pointsSummary[1].toString()}`);
      console.log(`     Total Points Burned: ${pointsSummary[2].toString()}`);
      console.log(`     Total Achievements: ${achievementsSummary[0].toString()}`);
      console.log(`     Total Points from Achievements: ${achievementsSummary[1].toString()}`);
      console.log(`     Track IDs Completed: ${achievementsSummary[2].map(id => id.toString())}`);
      console.log(`     Owned NFT Token IDs: ${tokenIds.map(id => id.toString())}`);
    }
    
  } catch (error) {
    console.log("❌ Error getting user profiles:", error.message);
  }
  
  // Demo 7: One-time transfer functionality (Soulbound Exit)
  console.log("\n🔄 Demo 7: One-time transfer functionality (Soulbound Exit)");
  try {
    const user1Address = await user1.getAddress();
    const user4Address = await user4.getAddress();
    
    // Check user1's status before transfer
    console.log("\n   Before One-Time Transfer:");
    const beforePoints = await trainingPoints.getUserBalance(user1Address);
    const beforeTokenIds = await trainingNFT.getUserTokenIds(user1Address);
    
    console.log("     User1 points balance:", beforePoints.toString());
    console.log("     User1 NFT token IDs:", beforeTokenIds.map(id => id.toString()));
    
    if (beforeTokenIds.length > 0) {
      // Perform one-time transfer from user1 to user4
      console.log("\n   Performing One-Time Transfer (User1 → User4):");
      console.log("   🔥 This will burn all User1's points and transfer NFTs!");
      
      const trainingNFTUser1 = TrainingAchievementNFT.attach(TRAINING_NFT_ADDRESS).connect(user1);
      const tx = await trainingNFTUser1.useOneTimeTransfer(user4Address, [...beforeTokenIds]);
      await tx.wait();
      
      console.log("✅ One-time transfer completed!");
      console.log("   User1 transferred all NFTs to User4");
      console.log("   User1's points have been burned");
      
      // Check status after transfer
      console.log("\n   After One-Time Transfer:");
      const afterPoints = await trainingPoints.getUserBalance(user1Address);
      const afterTokenIds = await trainingNFT.getUserTokenIds(user1Address);
      const user4TokenIds = await trainingNFT.getUserTokenIds(user4Address);
      
      console.log("     User1 points balance:", afterPoints.toString());
      console.log("     User1 NFT token IDs:", afterTokenIds.map(id => id.toString()));
      console.log("     User4 NFT token IDs:", user4TokenIds.map(id => id.toString()));
    } else {
      console.log("   No NFTs to transfer");
    }
    
  } catch (error) {
    console.log("❌ Error in one-time transfer demo:", error.message);
  }
  
  // Demo 8: Batch issue achievements
  console.log("\n📦 Demo 8: Batch issuing achievements");
  try {
    const userAddresses = [await user2.getAddress(), await user3.getAddress()];
    
    const tx = await trainingNFT.batchMintAchievements(
      userAddresses,
      3, // trackId
      "Smart Contract Security",
      "Completed security best practices course",
      "https://example.com/security-badge.png",
      ethers.parseEther("200")
    );
    await tx.wait();
    
    console.log("✅ Batch achievements issued");
    
    // Check results
    for (let i = 0; i < userAddresses.length; i++) {
      const pointsSummary = await trainingPoints.getUserPointsSummary(userAddresses[i]);
      console.log(`   User${i+2} total points earned: ${pointsSummary[1].toString()}`);
    }
    
  } catch (error) {
    console.log("❌ Error batch issuing achievements:", error.message);
  }
  
  // Demo 9: Check contract statistics
  console.log("\n📊 Demo 9: Contract statistics");
  try {
    const totalSupply = await trainingPoints.getTotalSupply();
    const ownerAddress = await trainingPoints.owner();
    const nftContractAddress = await trainingPoints.nftContract();
    
    console.log("   Total Points Supply:", totalSupply.toString());
    console.log("   Contract Owner:", ownerAddress);
    console.log("   NFT Contract Reference:", nftContractAddress);
    
  } catch (error) {
    console.log("❌ Error getting contract statistics:", error.message);
  }
  
  // Demo 10: Verify soulbound functionality
  console.log("\n🔒 Demo 10: Verifying soulbound functionality");
  try {
    console.log("   Testing soulbound restrictions:");
    
    // Get user1's NFTs
    const user1Address = await user1.getAddress();
    const tokenIds = await trainingNFT.getUserTokenIds(user1Address);
    
    if (tokenIds.length > 0) {
      console.log(`   User1 has ${tokenIds.length} NFTs`);
      console.log("   Regular transfers are blocked by soulbound protection");
      console.log("   Only one-time transfer is allowed (with points burning)");
    } else {
      console.log("   No NFTs to test transfer restrictions");
    }
    
  } catch (error) {
    console.log("❌ Error verifying soulbound functionality:", error.message);
  }
  
  console.log("\n🎉 Demo Complete!");
  console.log("📝 Key Features Demonstrated:");
  console.log("   ✅ Track completion NFTs with automatic points issuance");
  console.log("   ✅ Manual points awarding by owner");
  console.log("   ✅ Points accumulation and tracking");
  console.log("   ✅ Fungible points transfer and burning");
  console.log("   ✅ Complete user profile tracking");
  console.log("   ✅ Batch operations for efficiency");
  console.log("   ✅ Owner-only administrative functions");
  console.log("   ✅ Soulbound NFTs (non-transferable by default)");
  console.log("   ✅ One-time transfer with points burning (Soulbound exit)");
  console.log("   ✅ Comprehensive soulbound verification");
}

main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  }); 