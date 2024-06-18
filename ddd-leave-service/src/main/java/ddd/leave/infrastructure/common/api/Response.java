package ddd.leave.infrastructure.common.api;

public class Response {

    Status status;
    String msg;
    Object data;

    public static Response ok(){
        Response response = new Response();
        response.setStatus(Status.SUCCESS);
        return response;
    }

    public static Response ok(Object data){
        Response response = new Response();
        response.setStatus(Status.SUCCESS);
        response.setData(data);
        return response;
    }

    public static Response failed(String msg){
        Response response = new Response();
        response.setStatus(Status.FAILED);
        response.setMsg(msg);
        return response;
    }

    public Response(Status status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Response() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum Status{
        SUCCESS, FAILED
    }
}
