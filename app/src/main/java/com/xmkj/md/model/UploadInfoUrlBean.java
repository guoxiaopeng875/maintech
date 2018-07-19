package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/18.
 */

public class UploadInfoUrlBean {


    /**
     * Success : true
     * Data : {"FileId":"5b4f4a486c95a32cb878c1ff","FileName":"jpeg_20180718_221014_1787864611","FileUrl":"/Upload/attachment/5b4f4a486c95a32cb878c1ff.jpg"}
     * Message : 上传成功
     */

    private boolean Success;
    private DataBean Data;
    private String Message;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public static class DataBean {
        /**
         * FileId : 5b4f4a486c95a32cb878c1ff
         * FileName : jpeg_20180718_221014_1787864611
         * FileUrl : /Upload/attachment/5b4f4a486c95a32cb878c1ff.jpg
         */

        private String FileId;
        private String FileName;
        private String FileUrl;

        public String getFileId() {
            return FileId;
        }

        public void setFileId(String FileId) {
            this.FileId = FileId;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFileUrl() {
            return FileUrl;
        }

        public void setFileUrl(String FileUrl) {
            this.FileUrl = FileUrl;
        }
    }
}
