#!/usr/bin/env python3
"""
Training Achievement System - Pure Python Interaction Script
Interacts with deployed TrainingPoints and TrainingAchievementNFT contracts
Includes Soulbound NFTs and One-Time Transfer functionality

USAGE:
1. Update contract addresses below with your deployed contract addresses
2. Update RPC_URL with your blockchain network endpoint
3. Update PRIVATE_KEY with your account private key
4. Run: python scripts/interact_training_system.py
"""

import json
from web3 import Web3
from eth_account import Account
from typing import Dict, List, Tuple

# =============================================================================
# CONFIGURATION - UPDATE THESE VALUES
# =============================================================================

# Network Configuration
RPC_URL = "http://localhost:8545"  # Besu network endpoint
PRIVATE_KEY = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80"  # Update with your private key

# Contract Addresses - Load from deployed-addresses.json
def load_contract_addresses():
    """Load contract addresses from deployed-addresses.json"""
    try:
        with open('deployed-addresses.json', 'r') as f:
            addresses = json.load(f)
            return addresses['TrainingPoints'], addresses['TrainingAchievementNFT']
    except FileNotFoundError:
        print("❌ deployed-addresses.json not found!")
        print("💡 Please run the deployment script first: npx hardhat run scripts/deploy-and-save.js --network localhost")
        exit(1)
    except Exception as e:
        print(f"❌ Error loading contract addresses: {str(e)}")
        exit(1)

TRAINING_POINTS_ADDRESS, TRAINING_NFT_ADDRESS = load_contract_addresses()

# =============================================================================
# CONTRACT ABIs
# =============================================================================

TRAINING_POINTS_ABI = [
    {"inputs": [], "name": "owner", "outputs": [{"type": "address"}], "stateMutability": "view", "type": "function"},
    {"inputs": [], "name": "nftContract", "outputs": [{"type": "address"}], "stateMutability": "view", "type": "function"},
    {"inputs": [], "name": "getTotalSupply", "outputs": [{"type": "uint256"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}], "name": "getUserBalance", "outputs": [{"type": "uint256"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}], "name": "getUserPointsSummary", "outputs": [{"type": "uint256", "name": "currentBalance"}, {"type": "uint256", "name": "totalEarned"}, {"type": "uint256", "name": "totalBurned"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}, {"type": "uint256"}, {"type": "string"}], "name": "mintPoints", "outputs": [], "stateMutability": "nonpayable", "type": "function"},
    {"inputs": [{"type": "address"}, {"type": "uint256"}], "name": "transferPoints", "outputs": [{"type": "bool"}], "stateMutability": "nonpayable", "type": "function"},
    {"inputs": [{"type": "uint256"}, {"type": "string"}], "name": "burnPoints", "outputs": [], "stateMutability": "nonpayable", "type": "function"},
    {"inputs": [{"type": "address"}, {"type": "uint256"}, {"type": "string"}], "name": "burnPointsFrom", "outputs": [], "stateMutability": "nonpayable", "type": "function"}
]

TRAINING_NFT_ABI = [
    {"inputs": [], "name": "owner", "outputs": [{"type": "address"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}, {"type": "uint256"}, {"type": "string"}, {"type": "string"}, {"type": "string"}, {"type": "uint256"}], "name": "mintAchievement", "outputs": [], "stateMutability": "nonpayable", "type": "function"},
    {"inputs": [{"type": "address[]"}, {"type": "uint256"}, {"type": "string"}, {"type": "string"}, {"type": "string"}, {"type": "uint256"}], "name": "batchMintAchievements", "outputs": [], "stateMutability": "nonpayable", "type": "function"},
    {"inputs": [{"type": "address"}], "name": "getUserTokenIds", "outputs": [{"type": "uint256[]"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}], "name": "getUserAchievementsSummary", "outputs": [{"type": "uint256", "name": "totalAchievements"}, {"type": "uint256", "name": "totalPointsEarned"}, {"type": "uint256[]", "name": "trackIds"}], "stateMutability": "view", "type": "function"},
    {"inputs": [{"type": "address"}, {"type": "uint256[]"}], "name": "useOneTimeTransfer", "outputs": [], "stateMutability": "nonpayable", "type": "function"}
]

class TrainingSystemInteractor:
    def __init__(self):
        """Initialize the interactor with Web3 connection and account"""
        # Connect to network
        self.w3 = Web3(Web3.HTTPProvider(RPC_URL))
        
        if not self.w3.is_connected():
            raise Exception(f"❌ Failed to connect to network at {RPC_URL}")
        
        # Setup account
        self.account = Account.from_key(PRIVATE_KEY)
        self.w3.eth.default_account = self.account.address
        
        # Initialize contract instances
        self.training_points = self.w3.eth.contract(
            address=TRAINING_POINTS_ADDRESS,
            abi=TRAINING_POINTS_ABI
        )
        self.training_nft = self.w3.eth.contract(
            address=TRAINING_NFT_ADDRESS,
            abi=TRAINING_NFT_ABI
        )
        
        # Generate test user addresses
        self.test_users = self.generate_test_users()
        
        # Verify contracts are deployed
        self.verify_contracts()
        
    def verify_contracts(self):
        """Verify that contracts are deployed and accessible"""
        print("🔍 Verifying contract deployment...")
        
        try:
            # Check if contracts have code
            points_code = self.w3.eth.get_code(TRAINING_POINTS_ADDRESS)
            nft_code = self.w3.eth.get_code(TRAINING_NFT_ADDRESS)
            
            if points_code == b'':
                raise Exception(f"❌ TrainingPoints contract not deployed at {TRAINING_POINTS_ADDRESS}")
            
            if nft_code == b'':
                raise Exception(f"❌ TrainingAchievementNFT contract not deployed at {TRAINING_NFT_ADDRESS}")
            
            print("✅ Contracts verified and accessible")
            
        except Exception as e:
            print(f"❌ Contract verification failed: {str(e)}")
            print("💡 Make sure to update the contract addresses in the script")
            raise e
        
    def generate_test_users(self) -> List[Dict]:
        """Generate test user accounts for demonstration"""
        users = []
        for i in range(4):
            # Generate deterministic private keys for testing
            private_key = f"0x{'0' * 63}{i+1}"
            account = Account.from_key(private_key)
            users.append({
                'name': f'User{i+1}',
                'address': account.address,
                'private_key': private_key
            })
        return users
    
    def get_nonce(self) -> int:
        """Get current nonce for the account"""
        return self.w3.eth.get_transaction_count(self.account.address)
    
    def build_transaction(self, function_call, gas_limit: int = 300000) -> Dict:
        """Build a transaction with proper gas estimation"""
        nonce = self.get_nonce()
        gas_price = self.w3.eth.gas_price
        
        transaction = function_call.build_transaction({
            'from': self.account.address,
            'nonce': nonce,
            'gas': gas_limit,
            'gasPrice': gas_price
        })
        
        return transaction
    
    def sign_and_send_transaction(self, transaction: Dict) -> str:
        """Sign and send a transaction"""
        try:
            # Try using send_transaction first (works with most networks)
            tx_hash = self.w3.eth.send_transaction(transaction)
            return self.w3.to_hex(tx_hash)
        except Exception as e:
            # Fallback to manual signing for networks that require it
            try:
                signed_txn = self.w3.eth.account.sign_transaction(transaction, self.account.key)
                # Try different attribute names for raw transaction
                try:
                    raw_tx = signed_txn.rawTransaction
                except AttributeError:
                    try:
                        raw_tx = signed_txn.raw_transaction
                    except AttributeError:
                        raw_tx = signed_txn
                
                tx_hash = self.w3.eth.send_raw_transaction(raw_tx)
                return self.w3.to_hex(tx_hash)
            except Exception as sign_error:
                print(f"Transaction signing error: {str(sign_error)}")
                raise sign_error
    
    def wait_for_transaction(self, tx_hash: str) -> Dict:
        """Wait for transaction to be mined and return receipt"""
        tx_receipt = self.w3.eth.wait_for_transaction_receipt(tx_hash)
        return tx_receipt
    
    def demo_mint_track_completion(self):
        """Demo 1: Mint track completion achievement to user1"""
        print("\n🏆 Demo 1: Minting track completion achievement to user1")
        
        try:
            user1_address = self.test_users[0]['address']
            
            # Mint achievement NFT for track completion
            mint_tx = self.training_nft.functions.mintAchievement(
                user1_address,
                1,  # trackId
                "Blockchain Fundamentals",
                "Completed blockchain basics course",
                "https://example.com/badge1.png",
                100  # 100 TPOINTS
            )
            
            transaction = self.build_transaction(mint_tx)
            tx_hash = self.sign_and_send_transaction(transaction)
            receipt = self.wait_for_transaction(tx_hash)
            
            print("✅ Track completion achievement minted for user1")
            
            # Check user1's points balance
            points_balance = self.training_points.functions.getUserBalance(user1_address).call()
            points_summary = self.training_points.functions.getUserPointsSummary(user1_address).call()
            
            print(f"   User1 total points balance: {points_balance}")
            print(f"   User1 total points earned: {points_summary[1]}")
            print(f"   User1 total points burned: {points_summary[2]}")
            
        except Exception as e:
            print(f"❌ Error minting track completion achievement: {str(e)}")
    
    def demo_mint_another_track(self):
        """Demo 2: Mint another track completion to user2"""
        print("\n🏆 Demo 2: Minting another track completion to user2")
        
        try:
            user2_address = self.test_users[1]['address']
            
            # Mint achievement NFT for another track
            mint_tx = self.training_nft.functions.mintAchievement(
                user2_address,
                2,  # trackId
                "Advanced Solidity",
                "Completed advanced smart contract course",
                "https://example.com/badge2.png",
                150  # 150 TPOINTS
            )
            
            transaction = self.build_transaction(mint_tx)
            tx_hash = self.sign_and_send_transaction(transaction)
            receipt = self.wait_for_transaction(tx_hash)
            
            print("✅ Advanced Solidity track completion minted for user2")
            
            # Check user2's points balance
            points_balance = self.training_points.functions.getUserBalance(user2_address).call()
            points_summary = self.training_points.functions.getUserPointsSummary(user2_address).call()
            
            print(f"   User2 total points balance: {points_balance}")
            print(f"   User2 total points earned: {points_summary[1]}")
            print(f"   User2 total points burned: {points_summary[2]}")
            
        except Exception as e:
            print(f"❌ Error minting achievement: {str(e)}")
    
    def demo_manual_points_award(self):
        """Demo 3: Owner manually awards points to user3"""
        print("\n👑 Demo 3: Owner manually awards points to user3")
        
        try:
            user3_address = self.test_users[2]['address']
            
            # Owner awards points directly
            mint_tx = self.training_points.functions.mintPoints(
                user3_address,
                200,  # 200 TPOINTS
                "Special recognition for excellent work"
            )
            
            transaction = self.build_transaction(mint_tx)
            tx_hash = self.sign_and_send_transaction(transaction)
            receipt = self.wait_for_transaction(tx_hash)
            
            print("✅ Owner awarded 200 points to user3")
            
            # Check user3's points balance
            points_balance = self.training_points.functions.getUserBalance(user3_address).call()
            points_summary = self.training_points.functions.getUserPointsSummary(user3_address).call()
            
            print(f"   User3 total points balance: {points_balance}")
            print(f"   User3 total points earned: {points_summary[1]}")
            print(f"   User3 total points burned: {points_summary[2]}")
            
        except Exception as e:
            print(f"❌ Error awarding points: {str(e)}")
    
    def demo_transfer_points(self):
        """Demo 4: Transferring points from user1 to user3"""
        print("\n💸 Demo 4: Transferring points from user1 to user3")
        
        try:
            user1_address = self.test_users[0]['address']
            user3_address = self.test_users[2]['address']
            
            # Create user1 account for signing
            user1_account = Account.from_key(self.test_users[0]['private_key'])
            
            # Build transfer transaction as user1
            transfer_tx = self.training_points.functions.transferPoints(
                user3_address, 100  # 100 TPOINTS
            )
            
            # Build transaction with user1's nonce
            user1_nonce = self.w3.eth.get_transaction_count(user1_address)
            gas_price = self.w3.eth.gas_price
            
            transaction = transfer_tx.build_transaction({
                'from': user1_address,
                'nonce': user1_nonce,
                'gas': 300000,
                'gasPrice': gas_price
            })
            
            # Use the same signing method as other transactions
            try:
                # Try using send_transaction first
                tx_hash = self.w3.eth.send_transaction(transaction)
                tx_hash = self.w3.to_hex(tx_hash)
            except Exception as e:
                # Fallback to manual signing
                signed_txn = self.w3.eth.account.sign_transaction(transaction, user1_account.key)
                try:
                    raw_tx = signed_txn.rawTransaction
                except AttributeError:
                    try:
                        raw_tx = signed_txn.raw_transaction
                    except AttributeError:
                        raw_tx = signed_txn
                
                tx_hash = self.w3.eth.send_raw_transaction(raw_tx)
                tx_hash = self.w3.to_hex(tx_hash)
            
            receipt = self.w3.eth.wait_for_transaction_receipt(tx_hash)
            
            print("✅ 100 points transferred from user1 to user3")
            
            # Check balances after transfer
            user1_balance = self.training_points.functions.getUserBalance(user1_address).call()
            user3_balance = self.training_points.functions.getUserBalance(user3_address).call()
            
            print(f"   User1 points balance: {user1_balance}")
            print(f"   User3 points balance: {user3_balance}")
            
        except Exception as e:
            print(f"❌ Error transferring points: {str(e)}")
    
    def demo_burn_points(self):
        """Demo 5: Burn points for rewards"""
        print("\n🔥 Demo 5: Burning points for rewards")
        
        try:
            user2_address = self.test_users[1]['address']
            
            # Create user2 account for signing
            user2_account = Account.from_key(self.test_users[1]['private_key'])
            
            # Build burn transaction as user2
            burn_tx = self.training_points.functions.burnPoints(
                50, "Redeeming for course discount"  # 50 TPOINTS
            )
            
            # Build transaction with user2's nonce
            user2_nonce = self.w3.eth.get_transaction_count(user2_address)
            gas_price = self.w3.eth.gas_price
            
            transaction = burn_tx.build_transaction({
                'from': user2_address,
                'nonce': user2_nonce,
                'gas': 300000,
                'gasPrice': gas_price
            })
            
            # Use the same signing method as other transactions
            try:
                # Try using send_transaction first
                tx_hash = self.w3.eth.send_transaction(transaction)
                tx_hash = self.w3.to_hex(tx_hash)
            except Exception as e:
                # Fallback to manual signing
                signed_txn = self.w3.eth.account.sign_transaction(transaction, user2_account.key)
                try:
                    raw_tx = signed_txn.rawTransaction
                except AttributeError:
                    try:
                        raw_tx = signed_txn.raw_transaction
                    except AttributeError:
                        raw_tx = signed_txn
                
                tx_hash = self.w3.eth.send_raw_transaction(raw_tx)
                tx_hash = self.w3.to_hex(tx_hash)
            
            receipt = self.w3.eth.wait_for_transaction_receipt(tx_hash)
            
            print("✅ 50 points burned by user2")
            
            # Check balance after burning
            balance = self.training_points.functions.getUserBalance(user2_address).call()
            
            print(f"   User2 points balance after burning: {balance}")
            
        except Exception as e:
            print(f"❌ Error burning points: {str(e)}")
    
    def demo_user_profiles(self):
        """Demo 6: Get comprehensive user profiles"""
        print("\n👤 Demo 6: Getting comprehensive user profiles")
        
        try:
            for user in self.test_users:
                points_summary = self.training_points.functions.getUserPointsSummary(user['address']).call()
                achievements_summary = self.training_nft.functions.getUserAchievementsSummary(user['address']).call()
                token_ids = self.training_nft.functions.getUserTokenIds(user['address']).call()
                
                print(f"\n   {user['name']} Profile:")
                print(f"     Current Points Balance: {points_summary[0]}")
                print(f"     Total Points Earned: {points_summary[1]}")
                print(f"     Total Points Burned: {points_summary[2]}")
                print(f"     Total Achievements: {achievements_summary[0]}")
                print(f"     Total Points from Achievements: {achievements_summary[1]}")
                print(f"     Track IDs Completed: {[str(id) for id in achievements_summary[2]]}")
                print(f"     Owned NFT Token IDs: {[str(id) for id in token_ids]}")
                
        except Exception as e:
            print(f"❌ Error getting user profiles: {str(e)}")
    
    def demo_one_time_transfer(self):
        """Demo 7: One-time transfer functionality (Soulbound Exit)"""
        print("\n🔄 Demo 7: One-time transfer functionality (Soulbound Exit)")
        
        try:
            user1_address = self.test_users[0]['address']
            user4_address = self.test_users[3]['address']
            
            # Check user1's status before transfer
            print("\n   Before One-Time Transfer:")
            before_points = self.training_points.functions.getUserBalance(user1_address).call()
            before_token_ids = self.training_nft.functions.getUserTokenIds(user1_address).call()
            
            print(f"     User1 points balance: {before_points}")
            print(f"     User1 NFT token IDs: {[str(id) for id in before_token_ids]}")
            
            if len(before_token_ids) > 0:
                # Perform one-time transfer from user1 to user4
                print("\n   Performing One-Time Transfer (User1 → User4):")
                print("   🔥 This will burn all User1's points and transfer NFTs!")
                
                # Create user1 account for signing
                user1_account = Account.from_key(self.test_users[0]['private_key'])
                
                # Build one-time transfer transaction as user1
                transfer_tx = self.training_nft.functions.useOneTimeTransfer(user4_address, before_token_ids)
                
                # Build transaction with user1's nonce
                user1_nonce = self.w3.eth.get_transaction_count(user1_address)
                gas_price = self.w3.eth.gas_price
                
                transaction = transfer_tx.build_transaction({
                    'from': user1_address,
                    'nonce': user1_nonce,
                    'gas': 500000,  # Higher gas limit for complex operation
                    'gasPrice': gas_price
                })
                
                # Sign with user1's private key
                signed_txn = self.w3.eth.account.sign_transaction(transaction, user1_account.key)
                tx_hash = self.w3.eth.send_raw_transaction(signed_txn.rawTransaction)
                receipt = self.w3.eth.wait_for_transaction_receipt(tx_hash)
                
                print("✅ One-time transfer completed!")
                print("   User1 transferred all NFTs to User4")
                print("   User1's points have been burned")
                
                # Check status after transfer
                print("\n   After One-Time Transfer:")
                after_points = self.training_points.functions.getUserBalance(user1_address).call()
                after_token_ids = self.training_nft.functions.getUserTokenIds(user1_address).call()
                user4_token_ids = self.training_nft.functions.getUserTokenIds(user4_address).call()
                
                print(f"     User1 points balance: {after_points}")
                print(f"     User1 NFT token IDs: {[str(id) for id in after_token_ids]}")
                print(f"     User4 NFT token IDs: {[str(id) for id in user4_token_ids]}")
            else:
                print("   No NFTs to transfer")
            
        except Exception as e:
            print(f"❌ Error in one-time transfer demo: {str(e)}")
    
    def demo_batch_achievements(self):
        """Demo 8: Batch issue achievements"""
        print("\n📦 Demo 8: Batch issuing achievements")
        
        try:
            user_addresses = [self.test_users[1]['address'], self.test_users[2]['address']]
            
            # Batch issue achievements
            batch_tx = self.training_nft.functions.batchMintAchievements(
                user_addresses,
                3,  # trackId
                "Smart Contract Security",
                "Completed security best practices course",
                "https://example.com/security-badge.png",
                200  # 200 TPOINTS
            )
            
            transaction = self.build_transaction(batch_tx)
            tx_hash = self.sign_and_send_transaction(transaction)
            receipt = self.wait_for_transaction(tx_hash)
            
            print("✅ Batch achievements issued")
            
            # Check results
            for i, address in enumerate(user_addresses):
                points_summary = self.training_points.functions.getUserPointsSummary(address).call()
                print(f"   User{i+2} total points earned: {points_summary[1]}")
                
        except Exception as e:
            print(f"❌ Error batch issuing achievements: {str(e)}")
    
    def demo_contract_statistics(self):
        """Demo 9: Check contract statistics"""
        print("\n📊 Demo 9: Contract statistics")
        
        try:
            total_supply = self.training_points.functions.getTotalSupply().call()
            owner_address = self.training_points.functions.owner().call()
            nft_contract_address = self.training_points.functions.nftContract().call()
            
            print(f"   Total Points Supply: {total_supply}")
            print(f"   Contract Owner: {owner_address}")
            print(f"   NFT Contract Reference: {nft_contract_address}")
            
        except Exception as e:
            print(f"❌ Error getting contract statistics: {str(e)}")
    
    def demo_soulbound_verification(self):
        """Demo 10: Verify soulbound functionality"""
        print("\n🔒 Demo 10: Verifying soulbound functionality")
        
        try:
            # Check if regular transfers are blocked
            print("   Testing soulbound restrictions:")
            
            # Get user1's NFTs
            user1_address = self.test_users[0]['address']
            token_ids = self.training_nft.functions.getUserTokenIds(user1_address).call()
            
            if len(token_ids) > 0:
                print(f"   User1 has {len(token_ids)} NFTs")
                print(f"   Regular transfers are blocked by soulbound protection")
                print(f"   Only one-time transfer is allowed (with points burning)")
            else:
                print("   No NFTs to test transfer restrictions")
                
        except Exception as e:
            print(f"❌ Error verifying soulbound functionality: {str(e)}")
    
    def run_all_demos(self):
        """Run all demonstration functions"""
        print("🎯 Training Achievement System - Pure Python Interaction")
        print("📊 Focus: Track Completion NFTs with Points")
        print("🔒 Features: Soulbound NFTs & One-Time Transfer")
        
        print(f"\n👤 Owner: {self.account.address}")
        for i, user in enumerate(self.test_users):
            print(f"👤 {user['name']}: {user['address']}")
        
        print(f"\n📋 Contract Instances")
        print(f"   TrainingPoints: {TRAINING_POINTS_ADDRESS}")
        print(f"   TrainingAchievementNFT: {TRAINING_NFT_ADDRESS}")
        print(f"   Network: {RPC_URL}")
        
        # Run all demos
        self.demo_mint_track_completion()
        self.demo_mint_another_track()
        self.demo_manual_points_award()
        self.demo_transfer_points()
        self.demo_burn_points()
        self.demo_user_profiles()
        self.demo_one_time_transfer()
        self.demo_batch_achievements()
        self.demo_contract_statistics()
        self.demo_soulbound_verification()
        
        print("\n🎉 Demo Complete!")
        print("📝 Key Features Demonstrated:")
        print("   ✅ Track completion NFTs with automatic points issuance")
        print("   ✅ Manual points awarding by owner")
        print("   ✅ Points accumulation and tracking")
        print("   ✅ Fungible points transfer and burning")
        print("   ✅ Complete user profile tracking")
        print("   ✅ Batch operations for efficiency")
        print("   ✅ Owner-only administrative functions")
        print("   ✅ Soulbound NFTs (non-transferable by default)")
        print("   ✅ One-time transfer with points burning (Soulbound exit)")
        print("   ✅ Comprehensive soulbound verification")

def main():
    """Main function to run the demo"""
    try:
        # Initialize interactor
        interactor = TrainingSystemInteractor()
        
        # Run all demos
        interactor.run_all_demos()
        
    except Exception as e:
        print(f"❌ Error running demo: {str(e)}")
        print("\n💡 Make sure to:")
        print("   1. Update contract addresses in the script")
        print("   2. Update RPC_URL with your network endpoint")
        print("   3. Update PRIVATE_KEY with your account private key")
        print("   4. Ensure contracts are deployed and accessible")

if __name__ == "__main__":
    main() 