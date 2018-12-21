public class ReqPO {
    public ReqPO(){

    }

    public ReqPO(Params params, String method, String header) {
        this.params = params;
        this.method = method;
        this.header = header;
    }

    Params params;
    String method;
    String header;

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    class Params{
        public Params(){

        }
        public Params(String image) {
            this.image = image;
        }

        String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
