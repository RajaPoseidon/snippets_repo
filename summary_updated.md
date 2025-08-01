To enhance training engagement across multiple platforms within the firm, we are building a blockchain-based gamification platform. This system reimagines traditional learning programs by converting them into verifiable and rewarding digital experiences, powered by smart contracts deployed on a private blockchain. The platform serves as a custodian, managing credential issuance and point’s awarding on behalf of users.
In this ecosystem, each training achievement is represented as a unique, non-transferable digital asset that serves as an immutable record of accomplishment. These credentials include metadata such as the course name, track ID, and completion timestamp. Their soulbound nature ensures that ownership remains permanently tied to the individual—providing a trusted and tamper-proof proof of learning. Alongside each achievement, the system issues points that hold tangible value. These points can be redeemed for real-world off chain rewards or transferred to peers as a gesture of appreciation. By linking verified learning outcomes to tokenized incentives, we are adding real monetary value to training accomplishments.
User Journey Overview
When a user completes a training track on any integrated platform, the system initiates the following blockchain-backed actions to recognize and reward the achievement:
The first step involves minting a soulbound NFT, a non-transferable credential that permanently represents the user’s accomplishment. This NFT cannot be traded or transferred between users, ensuring that the achievement remains verifiably tied to the original recipient.
Simultaneously, the system issues ERC-20 tokens (POINTS) to the user’s address. These fungible points are directly linked to the achievement and provide tangible value. Users may burn their points to redeem real-world rewards such as gift cards or branded merchandise. Alternatively, they can transfer points to peers as a form of recognition, fostering a culture of collaboration.
After minting, users can access a personalized dashboard where their achievements and point balances are displayed. Each credential is shown with its full metadata, providing a transparent learning history. The platform also includes plans to introduce a marketplace, enabling users to redeem rewards and exchange points—further extending utility and driving continued participation.
Smart Contract Summary
We have designed two core smart contracts to power this platform. Both are built using the OpenZeppelin library to enforce Ethereum standards and best practices for security, modularity, and maintainability.
TrainingAchievementNFT (Soul-bound Credential Contract)
This smart contract manages the creation and storage of non-transferable training credentials. It is implemented using OpenZeppelin’s ERC721 and Ownable modules, with transfer logic overridden to enforce soul-bound behavior.
Technical Features:
NFTs are minted upon validated training completion and include metadata such as: trackId, achievementName, description, timestamp, pointsReward, and imageURI.
Only the platform owner (custodian backend) is authorized to mint NFTs.
A one-time transfer mechanism (useOneTimeTransfer) allows users to migrate their NFTs to personal wallets. During this process, a proportional amount of ERC-20 points is burned to maintain reward integrity.
TrainingPoints (ERC-20 fungible Token Contract)
This contract implements the reward system using fungible ERC-20 tokens representing transferable training points. These points are directly tied to achievements and used for peer appreciation, reward redemption, and gamified incentives.
Key Technical Features:
Built on OpenZeppelin’s ERC20 and Ownable.
Points can only be minted by the TrainingAchievementNFT contract (after training completion) or the platform owner via mintPoints(address, amount, reason).
Points are fully divisible, enabling partial transfers and partial redemptions.
Users can burn points using burnPoints() to redeem off-chain rewards like gift cards and branded merchandise.
Inter-Contract Integration:
TrainingAchievementNFT connects to the TrainingPoints contract via setPointsContract().
On achievement minting, mintPoints() is called to award points.
On NFT migration, burnPointsFrom() is invoked to deduct corresponding points from the user’s balance.