class Main {

    // donne une vue du système avec les agents
    // Si vous voulez une vision plus claire et proche du rapport, nous conseillons de
    // s'interesser a la classe SimpleExecution
    public static void main(String[] args) {
        sma.Systeme systeme = new sma.Systeme(10, 12,12,12,12);
        systeme.startSystem();
    }
}
