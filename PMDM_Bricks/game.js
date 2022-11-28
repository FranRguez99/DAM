// Variables del juego
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");
var ballRadius = 10;
var x = canvas.width/2;
var y = canvas.height-30;
var dx = 4;
var dy = -4;
var paddleHeight = 10;
var paddleWidth = 75;
var paddleX = (canvas.width-paddleWidth)/2;
var rightPressed = false;
var leftPressed = false;
var brickRowCount = 7;
var brickColumnCount = 3;
var brickWidth = 50.7142857;
var brickHeight = 20;
var brickPadding = 10;
var brickOffsetTop = 35;
var brickOffsetLeft = 30;
var score = 0;
var lives = 3;
var colorBola = "#FF0000";

// Array para la generación de ladrillos
var bricks = [];
for(var c=0; c<brickColumnCount; c++) {
    bricks[c] = [];
    for(var r=0; r<brickRowCount; r++) {
        bricks[c][r] = { x: 0, y: 0, status: 1 };
    }
}

// Funciones para el movimiento del 'paddle' con las teclas y el ratón
document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.addEventListener("mousemove", mouseMoveHandler, false);


function keyDownHandler(e) {
    if(e.key == "Right" || e.key == "ArrowRight") {
        rightPressed = true;
    }
    else if(e.key == "Left" || e.key == "ArrowLeft") {
        leftPressed = true;
    }
}

function keyUpHandler(e) {
    if(e.key == "Right" || e.key == "ArrowRight") {
        rightPressed = false;
    }
    else if(e.key == "Left" || e.key == "ArrowLeft") {
        leftPressed = false;
    }
}

function mouseMoveHandler(e) {
    var relativeX = e.clientX - canvas.offsetLeft;
    if(relativeX > 0 && relativeX < canvas.width) {
        paddleX = relativeX - paddleWidth/2;
    }
}

// Función para la detección de colisiones contra los ladrillos
function collisionDetection() {
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
            var b = bricks[c][r];
            if(b.status == 1) {
                if(x > b.x && x < b.x+brickWidth && y > b.y && y < b.y+brickHeight) {
                    dy = -dy;
                    b.status = 0;
                    score++;
                    if(score == brickRowCount*brickColumnCount) {
                        alert("¡HAS GANADO, ENHORABUENA!");
                        document.location.reload();
                    }
                }
            }
        }
    }
}

// Funciones para generar los objetos del juego
function drawBall() {
    ctx.beginPath();
    ctx.arc(x, y, ballRadius, 0, Math.PI*2);
    ctx.fillStyle = colorBola;
    ctx.fill();
    ctx.closePath();
}

function drawPaddle() {
    ctx.beginPath();
    ctx.rect(paddleX, canvas.height-paddleHeight, paddleWidth, paddleHeight);
    ctx.fillStyle = "#0095DD";
    ctx.fill();
    ctx.closePath();
}
function drawBricks() {
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
            if(bricks[c][r].status == 1) {
                var brickX = (r*(brickWidth+brickPadding))+brickOffsetLeft;
                var brickY = (c*(brickHeight+brickPadding))+brickOffsetTop;
                bricks[c][r].x = brickX;
                bricks[c][r].y = brickY;
                ctx.beginPath();
                ctx.rect(brickX, brickY, brickWidth, brickHeight);
                // Gradiente para el color de los ladrillos
                var grd = ctx.createLinearGradient(brickX, brickY, brickX+brickWidth, brickY+brickHeight);
                grd.addColorStop(0, "#044586");
                grd.addColorStop(1, "#83b0ea");
                ctx.fillStyle = grd;
                ctx.fill();
                ctx.closePath();
            }
        }
    }
}

// Funciones para dibujar la interfaz del juego (puntuación y vidas)
function drawScore() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Puntuación: "+score, 8, 20);
}
function drawLives() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Vidas: "+lives, canvas.width-65, 20);
}

function drawTitle() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("DESTROZANDO LADRILLOS", 130, 20);
}

// Función que dibuja lo declarado anteriormente en el canvas
function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBricks();
    drawBall();
    drawPaddle();
    drawScore();
    drawLives();
    drawTitle();
    collisionDetection();
    // Detección de colisiones contra la pared o el 'paddle'
    if(x + dx > canvas.width-ballRadius || x + dx < ballRadius) {
        dx = -dx;
        colorBola = "#" + Math.floor(Math.random()*16777215).toString(16);
    }
    if(y + dy < ballRadius) {
        dy = -dy;
        colorBola = "#" + Math.floor(Math.random()*16777215).toString(16);

    }
    else if(y + dy > canvas.height-ballRadius) {
        if(x > paddleX && x < paddleX + paddleWidth) {
            dy = -dy;
            colorBola = "#" + Math.floor(Math.random()*16777215).toString(16);

        }
        else {
            lives--;
            if(!lives) {
                alert("FIN DEL JUEGO");
                document.location.reload();
            }
            else {
                x = canvas.width/2;
                y = canvas.height-30;
                dx = 6;
                dy = -6;
                paddleX = (canvas.width-paddleWidth)/2;
            }
        }
    }

    if(rightPressed && paddleX < canvas.width-paddleWidth) {
        paddleX += 7;
    }
    else if(leftPressed && paddleX > 0) {
        paddleX -= 7;
    }

    x += dx;
    y += dy;
    requestAnimationFrame(draw);
}

draw();