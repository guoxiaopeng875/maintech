package com.xmkj.md.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/15.
 */

public class FiledirsBean implements Serializable{

    private List<FileDirListBean> FileDirList;

    public List<FileDirListBean> getFileDirList() {
        return FileDirList;
    }

    public void setFileDirList(List<FileDirListBean> FileDirList) {
        this.FileDirList = FileDirList;
    }

    public static class FileDirListBean implements Serializable{
        /**
         * FileDirId : 5852370ca0a6b37918aad4ae
         * FileDirName : 融资租赁申请书
         */

        private String FileDirId;
        private String FileDirName;
        private List<String> listPicUrl = new ArrayList<>();
        private List<String> listFileId = new ArrayList<>();

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

        public List<String> getListPicUrl() {
            return listPicUrl;
        }

        public void setListPicUrl(List<String> listPicUrl) {
            this.listPicUrl = listPicUrl;
        }

        public List<String> getListFileId() {
            return listFileId;
        }

        public void setListFileId(List<String> listFileId) {
            this.listFileId = listFileId;
        }


    }
}
