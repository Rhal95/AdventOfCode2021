import {readFileSync} from "fs";

function readInput() {
  return readFileSync("input", "utf8");
}

function parseBinary(numberString: string): number[] {
  const result = Array(numberString.length);
  for (let i = 0; i < numberString.length; i++) {
    result[i] = parseInt(numberString.charAt(i));
  }
  return result;
}

function convertToNumber(binaryNumber: number[]): number {
  let result = 0;
  for (let i = 0; i < binaryNumber.length; i++) {
    if (binaryNumber[binaryNumber.length - 1 - i]) {
      result += 1 << i;
    }
  }
  return result;
}

function gammaRate(input: number[][]): number {
  const counter = new Array(input[0].length).fill(0);
  input.forEach(value => {
    for (let i = 0; i < counter.length; i++) {
      counter[i] += value[i] ? 1 : -1;
    }
  });
  return convertToNumber(counter.map(value => value <= 0 ? 0 : 1));
}

function epsilonRate(input: number[][]): number {
  const counter = new Array(input[0].length).fill(0);
  input.forEach(value => {
    for (let i = 0; i < counter.length; i++) {
      counter[i] += value[i] ? 1 : -1;
    }
  });
  return convertToNumber(counter.map(value => value <= 0 ? 1 : 0));
}

function oxygenGenRating(input: number[][]) {
  let workingCopy = [...input];
  for (let i = 0; i < input[0].length && workingCopy.length > 1; i++) {
    let bitCounter = 0;
    workingCopy.map(number => number[i]).forEach(bit => bitCounter += bit ? 1 : -1);
    if(bitCounter >= 0){
      workingCopy = workingCopy.filter(value => value[i]);
    }else {
      workingCopy = workingCopy.filter(value => !value[i]);
    }
  }
  return convertToNumber(workingCopy[0]);
}

function co2ScrubberRating(input: number[][]) {
  let workingCopy = [...input];
  for (let i = 0; i < input[0].length && workingCopy.length > 1; i++) {
    let bitCounter = 0;
    workingCopy.map(number => number[i]).forEach(bit => bitCounter += bit ? 1 : -1);
    if(bitCounter >= 0){
      workingCopy = workingCopy.filter(value => !value[i]);
    }else {
      workingCopy = workingCopy.filter(value => value[i]);
    }
  }
  return convertToNumber(workingCopy[0]);
}

const input = readInput();
// const input = "00100\n" +
//   "11110\n" +
//   "10110\n" +
//   "10111\n" +
//   "10101\n" +
//   "01111\n" +
//   "00111\n" +
//   "11100\n" +
//   "10000\n" +
//   "11001\n" +
//   "00010\n" +
//   "01010";
const binaryInput = input.trim().split("\n").map(parseBinary);

const gamma = gammaRate(binaryInput);
const epsilon = epsilonRate(binaryInput);

console.log("Gamma", gamma);
console.log("Epsilon", epsilon);
console.log("Power Consumption", gamma * epsilon);

const oxygen = oxygenGenRating(binaryInput);
const co2 = co2ScrubberRating(binaryInput);

console.log("Oxygen", oxygen);
console.log("Co2", co2);
console.log("Life Support", oxygen * co2);
