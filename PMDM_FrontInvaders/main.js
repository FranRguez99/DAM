// Definimos la clase en la que se basarán los objetos del juego
class GameObject {
    constructor(x, y, width, height, color) {
        // Define la posición del objeto
        this.x = x;
        this.y = y;

        // Define el tamaño del objeto
        this.width = width;
        this.height = height;

        // Define el color del objeto
        this.color = color;
    }

    // Dibuja los objetos en el canvas
    draw(ctx) {
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, this.width, this.height);
    }

    // Modifica la posición de los objetos
    update(dx, dy) {
        this.x += dx;
        this.y += dy;
    }

    // Control de colisiones con otros objetos
    collidesWith(obj) {
        return (this.x < obj.x + obj.width
            && this.x + this.width > obj.x
            && this.y < obj.y + obj.height
            && this.y + this.height > obj.y);
    }
}

// Clase bala que define el comportamiento de las balas en nuestro juego
class Bullet extends GameObject {
    constructor(x, y, width, height, color, dy) {
        super(x, y, width, height, color);
        // Dirección y de la bala (cambia entre jugador y enemigo)
        this.dy = dy;
    }

    update(x, y) {
        this.y += this.dy;
    }
}

// Clase nave que define el comportamiento de jugador y enemigos
class SpaceShip extends GameObject {
    constructor(x, y, width, height, color, canvasHeight) {
        super(x, y, width, height, color);
        // Altura del canvas
        this.canvasHeight = canvasHeight;
        // Tamaño de la bala de la nave
        this.bulletWidth = 4;
        this.bulletHeight = 8;
        // Color de la bala
        this.bulletColor = "#ffffff";
        // Balas disparadas por la nave
        this.bullets = [];
    }

    // Sobrescribe la función 'draw' para dibujar también las balas
    draw(ctx) {
        super.draw(ctx);
        // Dibuja las balas
        for (var i = 0; i < this.bullets.length; i++) {
            this.bullets[i].draw(ctx);
            this.bullets[i].update(0, 0);

            // Comprueba si la bala ha salido del canva
            if (this.bullets[i].y < 0 || this.bullets[i].y > this.canvasHeight) {
                // Elimina la bala del array
                this.bullets.splice(i, 1);
            }
        }
    }

    // Método que para disparar balas
    shoot(dy) {
        this.bullets.push(new Bullet(
            this.x + this.width / 2 - this.bulletWidth / 2,
            this.y - this.bulletHeight,
            this.bulletWidth,
            this.bulletHeight,
            this.bulletColor,
            dy
        ));
    }
}

// Clase jugador que extiende nave, configura las acciones del jugador
class Player extends SpaceShip {
    constructor(x, y, width, height, color, canvasHeight, canvasWidth) {
        super(x, y, width, height, color, canvasHeight);
        this.canvasWidth = canvasWidth;
    }

    draw(ctx){
        super.draw(ctx);


    }
    // Actualiza la posición del jugador
    update(dx, dy) {
        super.update(dx, dy);

        // Mantiene al jugador dentro del canvas
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x + this.width > this.canvasWidth) {
            this.x = this.canvasWidth - this.width;
        }
    }
}

var score = 0;

// Objeto vacío para guardar la configuración del juego
var game = {};


// Define el canvas y el contexto
game.canvas = document.getElementById('canvas');
game.ctx = game.canvas.getContext('2d');

// Color de fondo
game.background = "rgba(0,0,0,0)";

// Enemigos
game.enemiesEachLine = 10;
game.enemyLines = 5;
game.enemySpace = 70;
game.enemyFireRate = 1000;
game.enemyFireTimer = 0;
game.enemyDirection = 1;
game.enemyStep = 6;

// Función que controla el loop del juego
game.update = function() {
    // Dibuja el fondo del canva
    game.ctx.clearRect(0,0,canvas.width, canvas.height);
    game.ctx.fillStyle = game.background;
    game.ctx.fillRect(0, 0, game.canvas.width, game.canvas.height);

    // Dibuja la puntuación
    drawScore(game.ctx);

    // Dibuja al jugador
    game.player.draw(game.ctx);

    // Dibuja enemigos
    for (var i = 0; i < game.enemies.length; i++) {
        game.enemies[i].draw(game.ctx);
        game.enemies[i].update(game.enemyDirection, 0);
    }

    // Comprueba si has ganado
    if (game.enemies.length == 0) {
        // Comenzar de nuevo
        game.restart();
    }

    // Comprueba si el enemigo ha salido de la pantalla
    if (game.enemyDirection == 1)
    {
        // Encuentra al enemigo más cercano a la derecha de la pantalla
        var closestToRightSideEnemy = game.enemies[0];
        for (var i = 1; i < game.enemies.length; i++) {
            if (game.enemies[i].x > closestToRightSideEnemy.x) {
                closestToRightSideEnemy = game.enemies[i];
            }
        }

        // Comprueba si el enemigo de la derecha ha colisionado en la pared
        if (closestToRightSideEnemy.x +
            closestToRightSideEnemy.width > game.canvas.width) {
            // Invierte a los enemigos
            game.enemyDirection = -1;
            // Mueve a los enemigos hacia abajo
            for (var i = 0; i < game.enemies.length; i++) {
                game.enemies[i].update(0, game.enemyStep);
            }
        }
    }
    else if (game.enemyDirection == -1)
    {
        // Encuentra al enemigo más cercano a la izquierda de la pantalla
        var closestToLeftSideEnemy = game.enemies[0];
        for (var i = 1; i < game.enemies.length; i++) {
            if (game.enemies[i].x < closestToLeftSideEnemy.x) {
                closestToLeftSideEnemy = game.enemies[i];
            }
        }

        // Comprueba si el enemigo de la derecha ha colisionado en la pared
        if (closestToLeftSideEnemy.x < 0) {
            // Invierte a los enemigos
            game.enemyDirection = 1;
            // Mueve a los enemigos hacia abajo
            for (var i = 0; i < game.enemies.length; i++) {
                game.enemies[i].update(0, game.enemyStep);
            }
        }
    }

    // Contador de balas enemigas
    game.enemyFireTimer += Math.random() * 10;
    if (game.enemyFireTimer > game.enemyFireRate) {
        game.enemyFireTimer = 0;
        // Disparo aleatorio de balas
        game.enemies[Math.floor(Math.random() * game.enemies.length)].shoot(5);
    }

    // Comprobar si la bala del jugador colisiona con el enemigo
    for (var i = 0; i < game.player.bullets.length; i++) {
        for (var j = 0; j < game.enemies.length; j++) {
            if (game.enemies[j].collidesWith(game.player.bullets[i])) {
                score++;
                game.enemies.splice(j, 1);
                game.player.bullets.splice(i, 1);
                break;
            }
        }
    }

    // Comprobar si la bala del enemigo colisiona con el jugador
    for (var i = 0; i < game.enemies.length; i++) {
        for (var j = 0; j < game.enemies[i].bullets.length; j++) {
            if (game.player.collidesWith(game.enemies[i].bullets[j])) {
                // Reset the game
                alert("¡HAS GANADO, ENHORABUENA!");
                game.restart();
                break;
            }
        }
    }

    // Comprueba si algún enemigo ha alcanzado al jugador
    for (var i = 0; i < game.enemies.length; i++) {
        if (game.enemies[i].y + game.enemies[i].height > game.player.y) {
            alert("FIN DEL JUEGO\n Puntuación: " + score);
            game.restart();
            break;
        }
    }
}

// Función para controlar la pulsación de teclas
game.keydown = function(e) {
    // Mover a la izquierda
    if (e.keyCode == 37 || e.keyCode == 65) {
        game.player.update(-5, 0);
    }
    // Mover a la derecha
    else if (e.keyCode == 39 || e.keyCode == 68) {
        game.player.update(5, 0);
    }
    // Disparar
    else if (e.keyCode == 32) {
        game.player.shoot(-5);
    }
}

// Función que comienza el loop del juego
game.init = function() {
    // Intervalo del loop
    game.interval = setInterval(game.update, 1000 / 60);

    // Configuración del jugador
    game.player = new Player(
        game.canvas.width / 2 - 50,
        game.canvas.height - 50,
        20,
        20,
        'rgb(36,192,26)',
        game.canvas.width
    );

    // Configuración de los enemigos
    game.enemies = [];
    for (var i = 0; i < game.enemyLines; i++) {
        for (var j = 0; j < game.enemiesEachLine; j++) {
            game.enemies.push(new SpaceShip(
                game.enemySpace + j * game.enemySpace,
                game.enemySpace + i * game.enemySpace,
                30,
                30,
                '#dd0000'
            ));
        }
    }
}

// Función que dibuja el marcador
function drawScore(ctx) {
    ctx.font = "32px Arial";
    ctx.fillStyle = "#ffffff";
    ctx.fillText("Puntuación: "+score, 8, 30);
}

// Función que detiene el loop del juego
game.stop = function() {
    clearInterval(game.interval);
}

// Función que resetea el juego
game.restart = function() {
    game.stop();
    game.init();
}

// Comienzo del juego al abrir la ventana
window.onload = game.init;

// Detección de pulsación de teclas
window.onkeydown = game.keydown;