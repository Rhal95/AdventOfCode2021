import {
  Board,
  mapToLines,
  onlyStraightLines,
  readInputLines
} from "../src/Day5.js";
import {expect} from "chai"

describe("Day5", function () {
  it("should read the input file", function () {
    const inputFile = "testInput";
    const data = readInputLines(inputFile);
    expect(data).deep.eq([
      "0,9 -> 5,9",
      "8,0 -> 0,8",
      "9,4 -> 3,4",
      "2,2 -> 2,1",
      "7,0 -> 7,4",
      "6,4 -> 2,0",
      "0,9 -> 2,9",
      "3,4 -> 1,4",
      "0,0 -> 8,8",
      "5,5 -> 8,2",
    ]);
  });

  it("should convert input to pairs of points", function () {
    const input = [
      "0,9 -> 5,9",
      "8,0 -> 0,8",
      "9,4 -> 3,4"];
    const lines = mapToLines(input);

    expect(lines).deep.equal(
      [
        {start: {x:0, y:9}, end: {x:5, y: 9}},
        {start: {x:8, y:0}, end: {x:0, y: 8}},
        {start: {x:9, y:4}, end: {x:3, y: 4}},
      ]
    )
  });

  it("should filter non straight lines", function () {
    const input = [
      {start: {x:0, y:9}, end: {x:5, y: 9}},
      {start: {x:8, y:0}, end: {x:0, y: 8}},
      {start: {x:9, y:4}, end: {x:3, y: 4}},
    ];
    const filtered = onlyStraightLines(input);
    expect(filtered).deep.equal([
      {start: {x:0, y:9}, end: {x:5, y: 9}},
      {start: {x:9, y:4}, end: {x:3, y: 4}},
    ])
  });

  describe("board", function () {
    it("should have size 3x3", function () {
      const input = [
        {start: {x:0, y:0}, end: {x:0, y: 2}},
        {start: {x:0, y:0}, end: {x:2, y: 0}},
      ]
      const board = new Board(input);
      expect(board.data).length(3);
      expect(board.data[0]).length(3);
    });

    it("should have size 4x4", function () {
      const input = [
        {start: {x:3, y:3}, end: {x:1, y: 3}},
        {start: {x:3, y:3}, end: {x:3, y: 1}},
      ]
      const board = new Board(input);
      expect(board.data).length(4);
      expect(board.data[0]).length(4);
    });

    it("should have size 5x5", function () {
      const input = [
        {start: {x:0, y:0}, end: {x:0, y: 4}},
        {start: {x:0, y:0}, end: {x:4, y: 0}},
      ]
      const board = new Board(input);

      expect(board.data).length(5);
      expect(board.data[0]).length(5);
    });

    it("should calculate board contents", function () {
      const input = [
        {start: {x:0, y:0}, end: {x:0, y: 2}},
        {start: {x:0, y:0}, end: {x:2, y: 0}},
      ]
      const board = new Board(input);
      board.calculateOverlaps();
      expect(board.data[0]).deep.equal([2,1,1]);
      expect(board.data[1]).deep.equal([1,0,0]);
      expect(board.data[2]).deep.equal([1,0,0]);
    });

    it("should count overlaps", function () {
      const input = [
        {start: {x:0, y:0}, end: {x:0, y: 2}},
        {start: {x:0, y:0}, end: {x:2, y: 0}},
        {start: {x:2, y:0}, end: {x:2, y: 2}},
      ]
      const board = new Board(input);
      board.calculateOverlaps();
      expect(board.countOverlaps()).equal(2)
    });
  });

  it("should calculate the test data solution", function () {
    const lines = mapToLines(readInputLines("testInput"));
    const board = new Board(onlyStraightLines(lines));
    board.calculateOverlaps();
    expect(board.countOverlaps()).equal(5);
  });

  it('should calculate the test data solution for part 2', function () {
    const lines = mapToLines(readInputLines("testInput"));
    const board = new Board(lines);
    board.calculateOverlaps();
    expect(board.countOverlaps()).equal(12);
  });

});
