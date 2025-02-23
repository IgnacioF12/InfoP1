/*
    Esta es su clase principal. El unico metodo que debe implementar es
    public String[] solve(Maze maze)
    pero es libre de crear otros metodos y clases en este u otro archivo que desee.
*/



/*
 * PORFAVOR, LEAN LOS COMENTARIOS PARA SABER QUE CHINGADOS ANDAMOS HACIENDO ANTES DE CAMBIAR COSAS COMO RETRASADOS
 * Soy Sergio ahi pa que sepan que ya hice mi parte no esten chingando que quiero grindear fortnite
 */

import java.util.ArrayList;

public class Solver{

    public Solver(){
        //Aqui ya probe inicializar las variables pero mejor se hace en el metodo porque como dependen de implementar
        // la vaina esa de Maze maze entonces mejor se inician alla, NO METAN NADA AQUI. (A no ser que de verdad lo necesiten)
    }


    public String solve(Maze maze){
        int anchoLab = maze.getWidth(); // estos 3 int solo son para saber cuanto mide el laberinto
        int altoLab = maze.getHeight();
        int profundidadLab = maze.getDepth();
        boolean [][][] x = new boolean[anchoLab][altoLab][profundidadLab]; //esto es para saber que casillas ya se visitaron
        ArrayList<String> caminoCorrecto = new ArrayList<>(); //Aqui se van metiendo las coordenadas en forma de string del camino correcto
        Node comienzo = maze.getStartingSpace(); //aqui es para saber el nodo donde se empieza
        if (miBorskiElQuePruebaVainas(comienzo, maze, x, caminoCorrecto) == true){
            return "[" + String.join(",", caminoCorrecto) + "]"; //aqui se devuelve el camino correcto en forma de string
        } else {
            return "No hay caminos validos";  //aqui pues ahi dice va lean
        }
    }

    private boolean miBorskiElQuePruebaVainas(Node actual, Maze maze, boolean [][][] x, ArrayList<String> caminoCorrecto){
        if (actual.isExit){
            return true;    //aqui es si ya llego a la salida entonces hacemos fiesta y ya retorna true y se termina alla arriba
        }

        x[actual.xIndex][actual.yIndex][actual.zIndex] = true;  //Aqui se marca donde estamos como que ya se visito, lo obvio pues
        Node siguientePos; //aqui creo el Nodo siguiente al que nos vamos a mover PERO AUN NO NOS HEMOS MOVIDO hay que probar

        siguientePos = maze.moveNorth(actual); //Aqui intento moverme
        if((siguientePos != actual) && (!x[siguientePos.xIndex][siguientePos.yIndex][siguientePos.zIndex]) && (!siguientePos.danger)){ //Aqui bla bla solo mira que no estemos nos estemos moviendo al mismo lugar que ya estabamos
            caminoCorrecto.add("N"); //y que sea posible obviamente osea que no haya sido visitado ya y que no hayan peligros, si si se puede entonces mete la coordenada a la lista de Strings para el camino correcto
            if(miBorskiElQuePruebaVainas(siguientePos, maze, x, caminoCorrecto)){   //Aqui se llama a si misma otra vez pero con siguientePos en vez de actual para que sea recursiva 
                return true; //si esta vaina retorna true entonces es que si se encontro un lugar que guia a la salida
            } else {
                caminoCorrecto.remove(caminoCorrecto.size()-1); //si no retorna true entonces pasito pa tras y probamos con otra coordenada
            }
        }

        /*
         * Basicamente para el resto de coordenadsas hay que hacer lo mismo que acabo de hacer yo pero con las otras va
         * entonces ahi se lo hechan que mi cerebro ya se fundio, y por cierto ahi se acuerdan del archivo txt que hay que pasar
         * con nuestros nombres y carnets y si alguien se quiere hacer gallo con lo de los puntos extra tons haganlo :)
         */

        return false; // esto solo lo puse porque no paraba de joder el coompilador ahi si lo necesitan mover muevanlo
    }
}