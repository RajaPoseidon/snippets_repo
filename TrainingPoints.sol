// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TrainingPoints is ERC20, Ownable {
    // Reference to the NFT contract
    address public nftContract;
    
    // Points tracking
    mapping(address => uint256) public totalPointsEarned;
    mapping(address => uint256) public totalPointsBurned;
    
    // Events
    event PointsMinted(address indexed user, uint256 amount, string reason);
    event PointsBurned(address indexed user, uint256 amount, string reason);
    event PointsTransferred(address indexed from, address indexed to, uint256 amount);
    event NFTContractSet(address indexed nftContract);
    
    constructor() ERC20("Training Points", "TPOINTS") Ownable(msg.sender) {}
    
    // Set NFT contract reference (owner only)
    function setNFTContract(address _nftContract) public onlyOwner {
        require(_nftContract != address(0), "Invalid NFT contract address");
        nftContract = _nftContract;
        emit NFTContractSet(_nftContract);
    }
    
    // Mint points (only callable by NFT contract or owner)
    function mintPoints(
        address user, 
        uint256 amount,
        string memory reason
    ) public {
        require(msg.sender == nftContract || msg.sender == owner(), "Unauthorized");
        require(amount > 0, "Amount must be greater than 0");
        require(user != address(0), "Invalid user address");
        
        // Mint points to user
        _mint(user, amount);
        
        // Update tracking
        totalPointsEarned[user] += amount;
        
        emit PointsMinted(user, amount, reason);
    }
    
    // Burn points (user can burn their own points)
    function burnPoints(uint256 amount, string memory reason) public {
        require(amount > 0, "Amount must be greater than 0");
        require(balanceOf(msg.sender) >= amount, "Insufficient balance");
        
        // Burn points
        _burn(msg.sender, amount);
        
        // Update tracking
        totalPointsBurned[msg.sender] += amount;
        
        emit PointsBurned(msg.sender, amount, reason);
    }
    
    // Burn points from specific user (only NFT contract or owner)
    function burnPointsFrom(address user, uint256 amount, string memory reason) public {
        require(msg.sender == nftContract || msg.sender == owner(), "Unauthorized");
        require(amount > 0, "Amount must be greater than 0");
        require(balanceOf(user) >= amount, "Insufficient balance");
        
        // Burn points
        _burn(user, amount);
        
        // Update tracking
        totalPointsBurned[user] += amount;
        
        emit PointsBurned(user, amount, reason);
    }
    
    // Transfer points to another user
    function transferPoints(address to, uint256 amount) public returns (bool) {
        require(to != address(0), "Invalid recipient");
        require(amount > 0, "Amount must be greater than 0");
        require(balanceOf(msg.sender) >= amount, "Insufficient balance");
        
        bool success = transfer(to, amount);
        if (success) {
            emit PointsTransferred(msg.sender, to, amount);
        }
        return success;
    }
    
    // Get user's total points earned
    function getUserTotalPointsEarned(address user) public view returns (uint256) {
        return totalPointsEarned[user];
    }
    
    // Get user's total points burned
    function getUserTotalPointsBurned(address user) public view returns (uint256) {
        return totalPointsBurned[user];
    }
    
    // Get user's current balance
    function getUserBalance(address user) public view returns (uint256) {
        return balanceOf(user);
    }
    
    // Get user's points summary
    function getUserPointsSummary(address user) public view returns (
        uint256 currentBalance,
        uint256 totalEarned,
        uint256 totalBurned
    ) {
        return (
            balanceOf(user),
            totalPointsEarned[user],
            totalPointsBurned[user]
        );
    }
    
    // Get total supply
    function getTotalSupply() public view returns (uint256) {
        return totalSupply();
    }
    
    // Get circulating supply (total supply - burned)
    function getCirculatingSupply() public view returns (uint256) {
        return totalSupply();
    }
} 