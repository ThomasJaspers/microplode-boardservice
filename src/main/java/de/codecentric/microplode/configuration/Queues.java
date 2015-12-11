package de.codecentric.microplode.configuration;

public class Queues {

    private Queues() {};
    public static final String BOARDSERVICE_GAMESERVICE_EXCHANGE =
            "microplode.boardservice.gameservice.exchange";
    public static final String BOARDSERVICE_PRESENTATION_SERVICE_EXCHANGE =
            "microplode.boardservice.presentationservice.exchange";

    public static final String GAMESERVICE_BOARDSERVICE = "microplode.gameservice.boardservice.queue";
    public static final String BOARDSERVICE_GAMESERVICE = "microplode.boardservice.gameservice.queue";
    public static final String BOARDSERVICE_PRESENTATIONSERVICE = "microplode.boardservice.presentationservice.queue";

}
