import {readFileSync} from "fs";

export function readInput(input = "input"){
    const numbers = readFileSync(input, "utf8").trim().split(",").map(v => Number.parseInt(v, 10));
    const result = [0,0,0,0,0,0,0,0,0];
    for (const number of numbers) {
        while(result.length < number-1) result.push(0);
        result[number]++;
    }
    return result;
}


export function passDay(input){
    let toAdd = input[0];
    for (let i = 0; i < input.length-1; i++) {
        input[i] = input[i+1];
        input[i+1] = 0;
    }
    if (toAdd > 0){
        input[6] += toAdd;
        input[8] = toAdd;
    }
}

export function passDays(input, days = 80){
    for (let i = 0; i < days; i++) {
        passDay(input);
    }
}

export function countFish(input){
    let fish = 0;
    for (const numFish of input) {
        fish += numFish;
    }
    return fish;
}

console.log(process.argv);
if(process.argv[process.argv.length-1] === "run"){
    const input = readInput();
    passDays(input);
    console.log(countFish(input));
    passDays(input, 256 - 80);
    console.log(countFish(input));
}
