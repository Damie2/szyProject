package com.smh.szyproject.mvp.bean;

public class VersionResult {
    /**
     * data : {"id":"4","name":"1.5","remark":"1.重点优化分享体验，建议升级！\r\n2.修复其他Bug。","code":"6","path":"http://ys-xw-ad.b0.upaiyun.com/yzyz/v1.0/app-yzyz-self-release.apk","package_name":"com.swkj.em","must_update":"1","md5":"5","created_at":"1477359394"}
     * code : 0
     * timeAt : 1526523451347
     * info :
     */

    private DataBean data;
    private int code;
    private long timeAt;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(long timeAt) {
        this.timeAt = timeAt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class DataBean {
        /**
         * id : 4
         * name : 1.5
         * remark : 1.重点优化分享体验，建议升级！
         2.修复其他Bug。
         * code : 6
         * path : http://ys-xw-ad.b0.upaiyun.com/yzyz/v1.0/app-yzyz-self-release.apk
         * package_name : com.swkj.em
         * must_update : 1
         * md5 : 5
         * created_at : 1477359394
         */

        private String id;
        private String name;
        private String remark;
        private String code;
        private String path;
        private String package_name;
        private String must_update;
        private String md5;
        private String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getMust_update() {
            return must_update;
        }

        public void setMust_update(String must_update) {
            this.must_update = must_update;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }


//    /**
//     * code : 200
//     * updateInfo : {"hasUpdate":false,"isSilent":false,"isForce":false,"isAutoInstall":true,"isIgnorable":true,"maxTimes":0,"versionCode":1,"versionName":"v1.0.1","updateContent":"","url":"http://money.9oe.com/down/00.apk","md5":"","size":9010197}
//     */
//
//    private int code;
//    private UpdateInfoBean updateInfo;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public UpdateInfoBean getUpdateInfo() {
//        return updateInfo;
//    }
//
//    public void setUpdateInfo(UpdateInfoBean updateInfo) {
//        this.updateInfo = updateInfo;
//    }
//
//    public static class UpdateInfoBean {
//        /**
//         * hasUpdate : false
//         * isSilent : false
//         * isForce : false
//         * isAutoInstall : true
//         * isIgnorable : true
//         * maxTimes : 0
//         * versionCode : 1
//         * versionName : v1.0.1
//         * updateContent :
//         * url : http://money.9oe.com/down/00.apk
//         * md5 :
//         * size : 9010197
//         */
//
//        private boolean hasUpdate;
//        private boolean isSilent;
//        private boolean isForce;
//        private boolean isAutoInstall;
//        private boolean isIgnorable;
//        private int maxTimes;
//        private int versionCode;
//        private String versionName;
//        private String updateContent;
//        private String url;
//        private String md5;
//        private int size;
//
//        public boolean isHasUpdate() {
//            return hasUpdate;
//        }
//
//        public void setHasUpdate(boolean hasUpdate) {
//            this.hasUpdate = hasUpdate;
//        }
//
//        public boolean isIsSilent() {
//            return isSilent;
//        }
//
//        public void setIsSilent(boolean isSilent) {
//            this.isSilent = isSilent;
//        }
//
//        public boolean isIsForce() {
//            return isForce;
//        }
//
//        public void setIsForce(boolean isForce) {
//            this.isForce = isForce;
//        }
//
//        public boolean isIsAutoInstall() {
//            return isAutoInstall;
//        }
//
//        public void setIsAutoInstall(boolean isAutoInstall) {
//            this.isAutoInstall = isAutoInstall;
//        }
//
//        public boolean isIsIgnorable() {
//            return isIgnorable;
//        }
//
//        public void setIsIgnorable(boolean isIgnorable) {
//            this.isIgnorable = isIgnorable;
//        }
//
//        public int getMaxTimes() {
//            return maxTimes;
//        }
//
//        public void setMaxTimes(int maxTimes) {
//            this.maxTimes = maxTimes;
//        }
//
//        public int getVersionCode() {
//            return versionCode;
//        }
//
//        public void setVersionCode(int versionCode) {
//            this.versionCode = versionCode;
//        }
//
//        public String getVersionName() {
//            return versionName;
//        }
//
//        public void setVersionName(String versionName) {
//            this.versionName = versionName;
//        }
//
//        public String getUpdateContent() {
//            return updateContent;
//        }
//
//        public void setUpdateContent(String updateContent) {
//            this.updateContent = updateContent;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getMd5() {
//            return md5;
//        }
//
//        public void setMd5(String md5) {
//            this.md5 = md5;
//        }
//
//        public int getSize() {
//            return size;
//        }
//
//        public void setSize(int size) {
//            this.size = size;
//        }


}
