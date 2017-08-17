"use strict";
/**
 * This is an effort to reproduce the WhackAMole Class in TS/JS
 */
var WhackAMole = (function () {
    /**
     * Constructor
     */
    function WhackAMole(numAttempts, gridDimensions) {
        this.score = 0;
        this.molesLeft = 0;
        this.moleGrid = [];
        for (var i = 0; i < gridDimensions; i++) {
            this.moleGrid[i] = [];
            for (var j = 0; j < gridDimensions; j++) {
                this.moleGrid[i][j] = "*";
            }
        }
        this.attempts = numAttempts;
    }
    // Start methods
    /**
     * Dumps entire moleGrid to console
     * Shows Moles, Whacks, and empty spaces
     */
    WhackAMole.prototype.printGrid = function () {
        for (var i = 0; i < this.moleGrid.length; i++) {
            for (var j = 0; j < this.moleGrid.length; j++) {
                process.stdout.write(this.moleGrid[i][j]);
            }
            console.log();
        }
        console.log();
    };
    /**
     * Tries to place a mole
     * If nmber is out of range, returns false, or if is Mole already return false
     * If possible, incriments molesLeft, and changes tile to 'M'
     */
    WhackAMole.prototype.place = function (x, y) {
        if (x < 0 || y < 0) {
            return false;
        }
        else if (x > this.moleGrid.length - 1 || y > this.moleGrid.length - 1) {
            return false;
        }
        else if (this.moleGrid[x][y] === "M") {
            return false;
        }
        else if (this.moleGrid[x][y] === "*") {
            this.moleGrid[x][y] = "M";
            this.molesLeft++;
            return true;
        }
        else
            return false;
    };
    /**
     * User takes a shot at a mole
     * @param x - row value in grid
     * @param y - column value in grid
     */
    WhackAMole.prototype.whack = function (x, y) {
        if (this.moleGrid[x][y] === "M") {
            this.score++;
            this.molesLeft--;
            console.log("Mole HIT! at [" + x + "][" + y + "]");
            console.log();
            this.moleGrid[x][y] = "W";
        }
        this.attempts--;
    };
    WhackAMole.prototype.printGridToUser = function () {
        for (var i = 0; i < this.moleGrid.length; i++) {
            for (var j = 0; j < this.moleGrid.length; j++) {
                if (this.moleGrid[i][j] === "W") {
                    process.stdout.write("W");
                }
                else {
                    process.stdout.write("*");
                }
            }
            console.log();
        }
        console.log();
    };
    WhackAMole.prototype.showScore = function () {
        console.log("Score: " + this.score);
        console.log("Moles Remaining: " + this.molesLeft);
        console.log("Whacks Remaining: " + this.attempts);
        console.log();
    };
    return WhackAMole;
}());
function promptUser() {
    promptt.get(['x', 'y'], function (err, result) {
        if (err) {
            onErr(err);
            promptUser();
        }
        else if (parseInt(result.x) === -1 && parseInt(result.y) === -1) {
            console.log('We are done.');
        }
        else {
            console.log('  x: ' + result.x);
            console.log('  y: ' + result.y);
            game.whack(result.x, result.y);
            game.showScore();
            game.printGridToUser();
            if (game.attempts === 0) {
                console.log("Sorry, you are out of tries");
                game.printGrid();
            }
            else if (game.molesLeft === 0) {
                console.log("Congrats, you found them all!!!");
                game.printGrid();
            }
            else {
                promptUser();
            }
        }
    });
}
function onErr(err) {
    console.log(err);
    return 1;
}
var game = new WhackAMole(20, 10);
for (var i = 0; i < 10; i++) {
    var column = parseInt(Math.random() * 10);
    game.place(i, column);
}
game.printGrid();
game.showScore();
game.printGridToUser();
var promptt = require("prompt");
promptt.start();
promptUser();
// TODO   Sort out error issues
// How to allow errors gracefully on bad user input
