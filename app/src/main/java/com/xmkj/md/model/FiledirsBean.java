package com.xmkj.md.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/15.
 */

public class FiledirsBean implements Serializable {

    private List<FileDirListBean> FileDirList;

    public List<FileDirListBean> getFileDirList() {
        return FileDirList;
    }

    public void setFileDirList(List<FileDirListBean> FileDirList) {
        this.FileDirList = FileDirList;
    }

    public static class FileDirListBean implements Serializable {
        /**
         * FileDirId : 5852370ca0a6b37918aad4ae
         * FileDirName : 融资租赁申请书
         */

        private String FileDirId;
        private String FileDirName;
        private List<FileListBean> FileList = new ArrayList<>();

        public String getFileDirId() {
            return FileDirId;
        }

        public void setFileDirId(String FileDirId) {
            this.FileDirId = FileDirId;
        }

        public String getFileDirName() {
            return FileDirName;
        }

        public void setFileDirName(String FileDirName) {
            this.FileDirName = FileDirName;
        }

        public List<FileListBean> getFileList() {
            return FileList;
        }

        public void setFileList(List<FileListBean> listPicUrl) {
            this.FileList = listPicUrl;
        }


        public static class FileListBean {
            private String FileId;
            private String FileName;
            private String FileUrl;

            public String getFileId() {
                return FileId;
            }

            public void setFileId(String fileId) {
                FileId = fileId;
            }

            public String getFileName() {
                return FileName;
            }

            public void setFileName(String fileName) {
                FileName = fileName;
            }

            public String getFileUrl() {
                return FileUrl;
            }

            public void setFileUrl(String fileUrl) {
                FileUrl = fileUrl;
            }
        }
    }


}
