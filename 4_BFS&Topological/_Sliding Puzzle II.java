/**
 * 794. Sliding Puzzle II
On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.

If it is impossible to move from initial state to final state, return -1.

Example
Given an initial state:

[
 [2,8,3],
 [1,0,4],
 [7,6,5]
]
and a final state:

[
 [1,2,3],
 [8,0,4],
 [7,6,5]
]
Return 4
Explanation:

[                 [
 [2,8,3],          [2,0,3],
 [1,0,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [2,0,3],          [0,2,3],
 [1,8,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [0,2,3],          [1,2,3],
 [1,8,4],   -->    [0,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [1,2,3],          [1,2,3],
 [0,8,4],   -->    [8,0,4],
 [7,6,5]           [7,6,5]
]                 ]
Challenge
How to optimize the memory?
Can you solve it with A* algorithm?

 */