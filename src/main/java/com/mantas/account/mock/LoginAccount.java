package com.mantas.account.mock;

import lombok.Data;

import java.util.List;

@Data
public class LoginAccount {
    /**
     * name : Serati Ma
     * avatar : https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png
     * userid : 00000001
     * email : antdesign@alipay.com
     * signature : 海纳百川，有容乃大
     * title : 交互专家
     * group : 蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED
     * tags : [{"key":"0","label":"很有想法的"},{"key":"1","label":"专注设计"},{"key":"2","label":"辣~"},{"key":"3","label":"大长腿"},{"key":"4","label":"川妹子"},{"key":"5","label":"海纳百川"}]
     * notifyCount : 12
     * unreadCount : 11
     * country : China
     * geographic : {"province":{"label":"浙江省","key":"330000"},"city":{"label":"杭州市","key":"330100"}}
     * address : 西湖区工专路 77 号
     * phone : 0752-268888888
     */

    private String name = "TempUser";
    private String avatar = "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";
    private String userid = "00000001";
    private String email = "antdesign@alipay.com";
    private String signature = "海纳百川，有容乃大";
    private String title = "交互专家";
    private String group = "Demo";
    private int notifyCount;
    private int unreadCount;
    private String country;
    private GeographicBean geographic;
    private String address;
    private String phone;
    private List<TagsBean> tags;

    public static class GeographicBean {
        /**
         * province : {"label":"浙江省","key":"330000"}
         * city : {"label":"杭州市","key":"330100"}
         */

        private ProvinceBean province;
        private CityBean city;

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class ProvinceBean {
            /**
             * label : 浙江省
             * key : 330000
             */

            private String label;
            private String key;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public static class CityBean {
            /**
             * label : 杭州市
             * key : 330100
             */

            private String label;
            private String key;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }

    public static class TagsBean {
        /**
         * key : 0
         * label : 很有想法的
         */

        private String key;
        private String label;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
