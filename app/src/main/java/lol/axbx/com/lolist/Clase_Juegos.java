package lol.axbx.com.lolist;

public class Clase_Juegos {

        private String num_jugadores, tipo_juego, participantes;

        public Clase_Juegos() {
        }

        public Clase_Juegos(String num_jugadores, String tipo_juego, String participantes) {
            this.num_jugadores = num_jugadores;
            this.tipo_juego = tipo_juego;
            this.participantes = participantes;
        }

        public String getNumeroJugadores() {
            return num_jugadores;
        }

        public void setNumeroJugadores(String name) {
            this.num_jugadores = name;
        }

        public String getTipo_juego() {
            return tipo_juego;
        }

        public void setTipo_juego(String tipojuego) {
            this.tipo_juego = tipojuego;
        }

        public String getParticipantes() {
            return participantes;
        }

        public void setParticipantes(String partcipant) {
            this.participantes = partcipant;
        }
    }

