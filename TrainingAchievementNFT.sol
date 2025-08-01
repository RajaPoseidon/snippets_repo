// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TrainingAchievementNFT is ERC721, Ownable {
    // Reference to the points contract
    address public pointsContract;
    
    // Achievement structure
    struct Achievement {
        string achievementName;
        string description;
        string imageURI;
        uint256 pointsReward;
        uint256 timestamp;
        uint256 trackId;
    }
    
    // Token ID to Achievement mapping
    mapping(uint256 => Achievement) public tokenAchievements;
    
    // User's owned token IDs
    mapping(address => uint256[]) public userTokenIds;
    

    
    // Token counter
    uint256 private _tokenIdCounter;
    
    // Events
    event AchievementMinted(address indexed user, uint256 indexed tokenId, string achievementName, uint256 pointsReward);
    event PointsContractSet(address indexed pointsContract);
    event OneTimeTransferUsed(address indexed from, address indexed to, uint256[] tokenIds, uint256 burnedPoints);
    
    // Soulbound: Transfer disabled
    error SoulboundToken();
    
    constructor() ERC721("Training Achievement", "TRAIN") Ownable(msg.sender) {}
    
    // Set points contract reference (owner only)
    function setPointsContract(address _pointsContract) public onlyOwner {
        require(_pointsContract != address(0), "Invalid points contract address");
        pointsContract = _pointsContract;
        emit PointsContractSet(_pointsContract);
    }
    

    // Mint achievement NFT (owner only)
    function mintAchievement(
        address user,
        uint256 trackId,
        string memory achievementName,
        string memory description,
        string memory imageURI,
        uint256 pointsReward
    ) public onlyOwner {
        require(user != address(0), "Invalid user address");
        require(bytes(achievementName).length > 0, "Achievement name cannot be empty");
        require(pointsReward > 0, "Points reward must be greater than 0");
        
        // Mint NFT
        uint256 tokenId = _tokenIdCounter++;
        _mint(user, tokenId);
        
        // Store achievement data
        tokenAchievements[tokenId] = Achievement({
            achievementName: achievementName,
            description: description,
            imageURI: imageURI,
            pointsReward: pointsReward,
            timestamp: block.timestamp,
            trackId: trackId
        });
        
        // Add to user's token list
        userTokenIds[user].push(tokenId);
        
        // Mint points to user (immediate reward)
        if (pointsContract != address(0)) {
            _mintPointsToUser(user, pointsReward);
        }
        
        emit AchievementMinted(user, tokenId, achievementName, pointsReward);
    }
    
    // Batch mint achievements
    function batchMintAchievements(
        address[] memory users,
        uint256 trackId,
        string memory achievementName,
        string memory description,
        string memory imageURI,
        uint256 pointsReward
    ) public onlyOwner {
        for (uint256 i = 0; i < users.length; i++) {
            if (users[i] != address(0)) {
                mintAchievement(users[i], trackId, achievementName, description, imageURI, pointsReward);
            }
        }
    }
    
    // One-time transfer function (soulbound with one-time transfer capability)
    function useOneTimeTransfer(address to, uint256[] memory tokenIds) public {
        require(to != address(0), "Invalid recipient");
        require(tokenIds.length > 0, "No tokens specified");
        
        uint256 pointsToBurn = 0;
        
        // Calculate points to burn (only for the NFTs being transferred) and verify ownership
        for (uint256 i = 0; i < tokenIds.length; i++) {
            uint256 tokenId = tokenIds[i];
            require(ownerOf(tokenId) == msg.sender, "Not owner of token");
            pointsToBurn += tokenAchievements[tokenId].pointsReward;
        }
        
        // Transfer all NFTs to new address using internal transfer
        for (uint256 i = 0; i < tokenIds.length; i++) {
            uint256 tokenId = tokenIds[i];
            // Use internal transfer to bypass soulbound restrictions
            _internalTransfer(msg.sender, to, tokenId);
        }
        
        // Burn points associated with the transferred NFTs (but only what user has)
        if (pointsContract != address(0) && pointsToBurn > 0) {
            // Get user's current balance to avoid burning more than they have
            uint256 userBalance = _getUserBalance(msg.sender);
            uint256 actualBurnAmount = pointsToBurn > userBalance ? userBalance : pointsToBurn;
            
            if (actualBurnAmount > 0) {
                _burnUserPoints(msg.sender, actualBurnAmount);
            }
        }
        
        emit OneTimeTransferUsed(msg.sender, to, tokenIds, pointsToBurn);
    }
    
    // Helper function to get user's current balance
    function _getUserBalance(address user) internal view returns (uint256) {
        (bool success, bytes memory data) = pointsContract.staticcall(
            abi.encodeWithSignature("getUserBalance(address)", user)
        );
        require(success, "Failed to get user balance");
        return abi.decode(data, (uint256));
    }
    
    // Internal transfer function that bypasses soulbound restrictions
    function _internalTransfer(address from, address to, uint256 tokenId) internal {
        require(from != address(0), "Transfer from zero address");
        require(to != address(0), "Transfer to zero address");
        require(ownerOf(tokenId) == from, "Transfer from incorrect owner");
        
        // Update token ownership
        _update(to, tokenId, from);
        
        // Update user token lists
        _removeTokenFromUser(from, tokenId);
        _addTokenToUser(to, tokenId);
    }
    
    // Helper function to remove token from user's list
    function _removeTokenFromUser(address user, uint256 tokenId) internal {
        uint256[] storage userTokens = userTokenIds[user];
        for (uint256 i = 0; i < userTokens.length; i++) {
            if (userTokens[i] == tokenId) {
                userTokens[i] = userTokens[userTokens.length - 1];
                userTokens.pop();
                break;
            }
        }
    }
    
    // Helper function to add token to user's list
    function _addTokenToUser(address user, uint256 tokenId) internal {
        userTokenIds[user].push(tokenId);
    }
    
    // Get user's owned token IDs
    function getUserTokenIds(address user) public view returns (uint256[] memory) {
        return userTokenIds[user];
    }
    
    // Get achievement details
    function getAchievementDetails(uint256 tokenId) public view returns (
        address owner,
        string memory achievementName,
        string memory description,
        string memory imageURI,
        uint256 pointsReward,
        uint256 timestamp,
        uint256 trackId
    ) {
        require(ownerOf(tokenId) != address(0), "Token does not exist");
        
        Achievement memory achievement = tokenAchievements[tokenId];
        
        return (
            ownerOf(tokenId),
            achievement.achievementName,
            achievement.description,
            achievement.imageURI,
            achievement.pointsReward,
            achievement.timestamp,
            achievement.trackId
        );
    }
    
    // Get user's achievements summary
    function getUserAchievementsSummary(address user) public view returns (
        uint256 totalAchievements,
        uint256 totalPointsEarned,
        uint256[] memory trackIds
    ) {
        uint256[] memory tokenIds = userTokenIds[user];
        totalAchievements = tokenIds.length;
        
        // Calculate total points and collect track IDs
        uint256 points = 0;
        trackIds = new uint256[](tokenIds.length);
        
        for (uint256 i = 0; i < tokenIds.length; i++) {
            Achievement memory achievement = tokenAchievements[tokenIds[i]];
            points += achievement.pointsReward;
            trackIds[i] = achievement.trackId;
        }
        
        totalPointsEarned = points;
    }
    
    // Mint points to user (internal function)
    function _mintPointsToUser(address user, uint256 points) internal {
        (bool success, ) = pointsContract.call(
            abi.encodeWithSignature("mintPoints(address,uint256,string)", 
                user, points, "Achievement earned")
        );
        require(success, "Failed to mint points");
    }
    
    // Burn user's points (internal function)
    function _burnUserPoints(address user, uint256 points) internal {
        (bool success, ) = pointsContract.call(
            abi.encodeWithSignature("burnPointsFrom(address,uint256,string)", user, points, "One-time transfer used")
        );
        require(success, "Failed to burn points");
    }
    
    // Create token URI with achievement metadata
    function _createTokenURI(uint256 tokenId) internal view returns (string memory) {
        Achievement memory achievement = tokenAchievements[tokenId];
        
        string memory json = string(abi.encodePacked(
            '{"name": "', achievement.achievementName, '",',
            '"description": "', achievement.description, '",',
            '"image": "', achievement.imageURI, '",',
            '"attributes": [',
            '{"trait_type": "Track ID", "value": ', _uint2str(achievement.trackId), '},',
            '{"trait_type": "Points Reward", "value": ', _uint2str(achievement.pointsReward), '},',
            '{"trait_type": "Achievement Date", "value": "', _uint2str(achievement.timestamp), '"}',
            ']}'
        ));
        
        return json;
    }
    
    // Helper function to convert uint to string
    function _uint2str(uint256 _i) internal pure returns (string memory) {
        if (_i == 0) return "0";
        uint256 j = _i;
        uint256 length;
        while (j != 0) {
            length++;
            j /= 10;
        }
        bytes memory bstr = new bytes(length);
        uint256 k = length;
        while (_i != 0) {
            k -= 1;
            uint8 temp = (48 + uint8(_i - _i / 10 * 10));
            bytes1 b1 = bytes1(temp);
            bstr[k] = b1;
            _i /= 10;
        }
        return string(bstr);
    }
    
    // Override transfer functions to make NFT soulbound
    function _update(address to, uint256 tokenId, address auth) internal virtual override returns (address) {
        address from = _ownerOf(tokenId);
        
        // Allow minting but prevent regular transfers
        // Note: One-time transfers are handled in useOneTimeTransfer function
        if (from != address(0) && to != address(0)) {
            // Check if this is a one-time transfer (called from useOneTimeTransfer)
            // We'll allow it if the auth is the current owner
            if (auth != from) {
                revert SoulboundToken();
            }
        }
        
        return super._update(to, tokenId, auth);
    }
    
    // Token URI function
    function tokenURI(uint256 tokenId) public view virtual override returns (string memory) {
        require(_ownerOf(tokenId) != address(0), "Token does not exist");
        return _createTokenURI(tokenId);
    }
} 