#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct puzzle {
    unsigned char signals[10];
    unsigned char input[4];
};

struct puzzle convertToPuzzle(char* buf){
    struct puzzle result;
    result.signals[0] = 0;
    result.signals[1] = 0;
    result.signals[2] = 0;
    result.signals[3] = 0;
    result.signals[4] = 0;
    result.signals[5] = 0;
    result.signals[6] = 0;
    result.signals[7] = 0;
    result.signals[8] = 0;
    result.signals[9] = 0;
    result.input[0] = 0;
    result.input[1] = 0;
    result.input[2] = 0;
    result.input[3] = 0;


    unsigned int pos = 0;
    int marker = 0;
    for(int i = 0; buf[i] != '\0' && buf[i] != '\n'; i++){
        if(!marker){
            switch(buf[i]){
               case 'a': result.signals[pos] |= 0b0000001; break;
               case 'b': result.signals[pos] |= 0b0000010; break;
               case 'c': result.signals[pos] |= 0b0000100; break;
               case 'd': result.signals[pos] |= 0b0001000; break;
               case 'e': result.signals[pos] |= 0b0010000; break;
               case 'f': result.signals[pos] |= 0b0100000; break;
               case 'g': result.signals[pos] |= 0b1000000; break;
               case ' ': pos++; break;
               case '|': marker = 1; pos = 0; i++; break;
            }
        }else {
            switch(buf[i]){
               case 'a': result.input[pos] |= 0b0000001; break;
               case 'b': result.input[pos] |= 0b0000010; break;
               case 'c': result.input[pos] |= 0b0000100; break;
               case 'd': result.input[pos] |= 0b0001000; break;
               case 'e': result.input[pos] |= 0b0010000; break;
               case 'f': result.input[pos] |= 0b0100000; break;
               case 'g': result.input[pos] |= 0b1000000; break;
               case ' ': pos++; break;
            }
        }
    }

    return result;
}

int countBits(unsigned char n){
    int result = 0;
    while(n){
        if(n & 0x1) result++;
        n = n >> 1;
    }
    return result;
}
struct puzzleSolution {
    int oneFourSevenEight;
    int output;
};

struct puzzleSolution solve(struct puzzle puzzle){
    struct puzzleSolution solution;
    solution.oneFourSevenEight = 0;
    solution.output = 0;

    // count 1 4 7 and 8
    for(int i = 0; i < 4; i++){
        switch(countBits(puzzle.input[i])){
            case 2: case 4: case 7: case 3: solution.oneFourSevenEight++;
            default: break;
        }
    }

    // find out which number is what
    int digits[10] = {0,0, 0,0, 0,0, 0,0, 0,0};
    for(int i = 0; i < 10; i++){
        switch(countBits(puzzle.signals[i])){
            case 2: digits[1] = puzzle.signals[i]; break;
            case 4: digits[4] = puzzle.signals[i]; break;
            case 7: digits[8] = puzzle.signals[i]; break;
            case 3: digits[7] = puzzle.signals[i]; break;
            default: break;
        }
    }
     for(int i = 0; i < 10; i++){
        switch(countBits(puzzle.signals[i])){
            case 5: // 2, 5, 3
                if((((digits[4] | digits[7]) ^ digits[1]) & puzzle.signals[i]) == ((digits[4] | digits[7]) ^ digits[1])) digits[5] = puzzle.signals[i];
                else if((digits[1] & puzzle.signals[i]) == digits[1]) digits[3] = puzzle.signals[i];
                else digits[2] = puzzle.signals[i];
                break;
            case 6: // 6, 9, 0
                if((digits[4] & puzzle.signals[i]) == digits[4]) digits[9] = puzzle.signals[i];
                else if((digits[1] & puzzle.signals[i]) == digits[1]) digits[0] = puzzle.signals[i];
                else digits[6] = puzzle.signals[i];
                break;
            default: break;
        }
    }

    // look up numbers in digit array
    int base = 1;
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 10; j++){
            if(digits[j] == puzzle.input[3-i]){
                solution.output += base * j;
            }
        }
        base *= 10;
    }

    return solution;
}

int main(int argc, char** argv){
    printf("Day8\n\n");
    char* inputFile = argv[argc-1];
    FILE* fp = fopen(inputFile, "r");

    unsigned char bufflen = 100;
    char* buf = calloc(bufflen, sizeof(char));
    char* r = fgets(buf, bufflen, fp);

    int oneFourSevenEight = 0;
    int sumOfOutput = 0;

    int digits[10] = {0,0,0,0,0,0,0,0,0,0};

    while(r != NULL){
        struct puzzle puzzle = convertToPuzzle(buf);
        struct puzzleSolution solution = solve(puzzle);

        oneFourSevenEight += solution.oneFourSevenEight;
        sumOfOutput += solution.output;
        printf("%04d\n",solution.output);

        while(solution.output){
            digits[solution.output % 10]++;
            solution.output /= 10;
        }

        memset(buf, 0, bufflen);
        r = fgets(buf, bufflen, fp);
    }
    for(int i = 0; i < 10; i++){
        printf("%d ", digits[i]);
    }
    printf("\nCount 1478: %d\nSum: %d\n", oneFourSevenEight, sumOfOutput);
}
