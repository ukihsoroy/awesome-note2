package org.ko.aop.chain;

public abstract class Handler {

    private Handler sucessor;

    public Handler getSucessor() {
        return sucessor;
    }

    public void setSucessor(Handler sucessor) {
        this.sucessor = sucessor;
    }

    public void execute () {
        handlerProcess();
        if (sucessor != null) {
            sucessor.execute();
        }
    }

    protected abstract void handlerProcess ();
}
