import {readFileSync} from "fs";

export function readInputLines(path: string): string[] {
  return readFileSync(path, "utf8").trim().split("\n");
}

interface Point {
  x: number;
  y: number;
}

/**
 *
 * @param input form: "12,34"
 */
function readPoint(input: string): Point {
  const numbers = input.split(",", 2);
  const [x, y] = numbers.map(value => Number.parseInt(value, 10));
  return {x, y};
}

interface Line {
  start: Point;
  end: Point;
}

export function mapToLines(input: string[]): Line[] {
  return input
    .map(value => value.split(" -> ").map(readPoint))
    .map(([start, end]) => ({start, end}));
}


export function onlyStraightLines(input: Line[]) {
  return input.filter(({start, end}) => start.x === end.x || start.y === end.y);
}

export class Board {
  data: number[][];

  constructor(private lines: Line[]) {
    let x = 0, y = 0;
    for (const line of lines) {
      x = line.start.x > x ? line.start.x : x;
      y = line.start.y > y ? line.start.y : y;
      x = line.end.x > x ? line.end.x : x;
      y = line.end.y > y ? line.end.y : y;
    }

    this.data = new Array(x + 1).fill([])
      .map((_, __) => new Array(y + 1).fill(0));
  }

  calculateOverlaps() {
    for (const {start, end} of this.lines) {
      const deltaX = end.x - start.x;
      const deltaY = end.y - start.y;
      if (deltaX && deltaY) {
        const xDirection = deltaX > 0 ? 1 : -1;
        const yDirection = deltaY > 0 ? 1 : -1;
        for (let i = 0; i <= deltaX * xDirection; i++) {
          this.data[start.x + i * xDirection][start.y + i * yDirection]++;
        }
      } else
        if (deltaX) {
        const xDirection = deltaX > 0 ? 1 : -1;
        for (let i = 0; i <= deltaX * xDirection; i++) {
          this.data[start.x + i * xDirection][start.y]++;
        }
      } else if (deltaY) {
        const yDirection = deltaY > 0 ? 1 : -1;
        for (let i = 0; i <= deltaY * yDirection; i++) {
          this.data[start.x][start.y + i * yDirection]++;
        }
      }
    }
  }

  printBoard() {
    let result = "";
    for (const row of this.data) {
      for (const number of row) {
        result += number ? number : ".";
      }
      result += "\n";
    }
    console.log(result);
  }

  countOverlaps(): number {
    let overlaps = 0;

    for (const row of this.data) {
      for (const number of row) {
        if (number > 1) overlaps++;
      }
    }
    return overlaps;
  }
}


const lines = mapToLines(readInputLines("input"));

const boardPart1 = new Board(onlyStraightLines(lines));
boardPart1.calculateOverlaps();
console.log(boardPart1.countOverlaps());

const boardPart2 = new Board(lines);
boardPart2.calculateOverlaps();
console.log(boardPart2.countOverlaps());
