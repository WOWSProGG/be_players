package io.howtoarchitect.wows.players.model.api;

public class Account {
    private String status;
    private Meta meta;
    private Data[] data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (null == data) {
            return "Account [meta=" + meta + ", status=" + status + " .. but data is not read yet!! (ugghhh)";

        } else {
            var sb = new StringBuilder();
            var i = 0;
            for (Data d : data) {
                sb.append(" idx[" + i + "] ==> " + d.toString());
                i++;
            }
            return "Account [meta=" + meta + ", status=" + status + ", Data=" + sb + " ]";
        }
    }

}
