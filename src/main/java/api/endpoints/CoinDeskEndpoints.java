package api.endpoints;

public enum CoinDeskEndpoints {


    BPI_CURRENT_PRICE("v1/bpi/currentprice.json");


    public final String resource;

    CoinDeskEndpoints(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {return this.resource;}

}

