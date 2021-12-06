import {countFish, passDays, readInput} from "../src/day6.js";
import {expect} from "chai"

describe('Day 6', function () {
    it('should read input', function () {
        expect(readInput("testInput")).to.deep.equal([0,1,1,2,1,0,0,0,0]);
    });

    it('should calculate the correct fish after 1 day',function (){
            let input = readInput("testInput");
            passDays(input, 1);
            expect(countFish(input)).to.equal(5);
    });

    it('should calculate the correct fish after 2 day',function (){
        let input = readInput("testInput");
        passDays(input, 2);
        expect(countFish(input)).to.equal(6);
    });
    it('should calculate the correct fish after 3 day',function (){
        let input = readInput("testInput");
        passDays(input, 3);
        expect(countFish(input)).to.equal(7);
    });
    it('should calculate the correct fish after 4 day',function (){
        let input = readInput("testInput");
        passDays(input, 4);
        expect(countFish(input)).to.equal(9);
    });
    it('should calculate the correct fish after 5 day',function (){
        let input = readInput("testInput");
        passDays(input, 5);
        expect(countFish(input), input).to.equal(10);
    });
    it('should calculate the correct fish after 6 day',function (){
        let input = readInput("testInput");
        passDays(input, 6);
        expect(countFish(input), input).to.equal(10);
    });
    it('should calculate the correct fish after 7 day',function (){
        let input = readInput("testInput");
        const msg = "[" + input + "],[";
        passDays(input, 7);
        expect(countFish(input), msg + input + "]").to.equal(10);
    });
    it('should calculate the correct fish after 8 day',function (){
        let input = readInput("testInput");
        passDays(input, 8);
        expect(countFish(input), input).to.equal(10);
    });

    it('should get the correct number of fish after 18 days', function () {
        let input = readInput("testInput");
        passDays(input, 18);
        expect(countFish(input)).to.equal(26);
    });

    it('should get the correct number of fish after 80 days', function () {
        let input = readInput("testInput");
        passDays(input);
        expect(countFish(input)).to.equal(5934);
    });
    it('should get the correct number of fish after 256 days', function () {
        let input = readInput("testInput");
        passDays(input, 256);
        expect(countFish(input)).to.equal(26984457539);
    });
});
