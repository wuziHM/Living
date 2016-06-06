package com.living.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author：燕青 $ on 16/4/18 12:01
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class CountryWeatherBean extends NetModel {

    /**
     * aqi : {"city":{"aqi":"114","co":"1","no2":"41","o3":"65","pm10":"69","pm25":"114","qlty":"轻度污染","so2":"9"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-06-06 09:51","utc":"2016-06-06 01:51"}}
     * daily_forecast : [{"astro":{"sr":"04:46","ss":"19:40"},"cond":{"code_d":"101","code_n":"302","txt_d":"多云","txt_n":"雷阵雨"},"date":"2016-06-06","hum":"22","pcpn":"0.0","pop":"8","pres":"1007","tmp":{"max":"31","min":"20"},"vis":"10","wind":{"deg":"190","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"sr":"04:45","ss":"19:40"},"cond":{"code_d":"302","code_n":"100","txt_d":"雷阵雨","txt_n":"晴"},"date":"2016-06-07","hum":"49","pcpn":"6.7","pop":"90","pres":"1010","tmp":{"max":"24","min":"16"},"vis":"10","wind":{"deg":"146","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"04:45","ss":"19:41"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-06-08","hum":"10","pcpn":"0.0","pop":"1","pres":"1008","tmp":{"max":"32","min":"21"},"vis":"10","wind":{"deg":"41","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"04:45","ss":"19:41"},"cond":{"code_d":"100","code_n":"302","txt_d":"晴","txt_n":"雷阵雨"},"date":"2016-06-09","hum":"13","pcpn":"0.3","pop":"38","pres":"1004","tmp":{"max":"33","min":"22"},"vis":"10","wind":{"deg":"208","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"04:45","ss":"19:42"},"cond":{"code_d":"302","code_n":"101","txt_d":"雷阵雨","txt_n":"多云"},"date":"2016-06-10","hum":"24","pcpn":"3.1","pop":"57","pres":"1001","tmp":{"max":"32","min":"21"},"vis":"9","wind":{"deg":"196","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"04:45","ss":"19:42"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-06-11","hum":"41","pcpn":"7.1","pop":"56","pres":"1001","tmp":{"max":"26","min":"18"},"vis":"10","wind":{"deg":"130","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"sr":"04:45","ss":"19:43"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-06-12","hum":"66","pcpn":"7.5","pop":"46","pres":"998","tmp":{"max":"30","min":"20"},"vis":"10","wind":{"deg":"62","dir":"无持续风向","sc":"微风","spd":"7"}}]
     * hourly_forecast : [{"date":"2016-06-06 10:00","hum":"39","pop":"0","pres":"1010","tmp":"30","wind":{"deg":"145","dir":"东南风","sc":"微风","spd":"6"}},{"date":"2016-06-06 13:00","hum":"25","pop":"0","pres":"1008","tmp":"34","wind":{"deg":"184","dir":"南风","sc":"微风","spd":"7"}},{"date":"2016-06-06 16:00","hum":"20","pop":"0","pres":"1006","tmp":"36","wind":{"deg":"182","dir":"南风","sc":"微风","spd":"8"}},{"date":"2016-06-06 19:00","hum":"26","pop":"0","pres":"1007","tmp":"35","wind":{"deg":"167","dir":"东南风","sc":"微风","spd":"8"}},{"date":"2016-06-06 22:00","hum":"39","pop":"5","pres":"1009","tmp":"33","wind":{"deg":"167","dir":"东南风","sc":"微风","spd":"7"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"25","hum":"64","pcpn":"0","pres":"1011","tmp":"23","vis":"7","wind":{"deg":"91","dir":"东北风","sc":"4-5","spd":"19"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较不舒适","txt":"白天天气多云，同时会感到有些热，不很舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较适宜","txt":"天气较好，户外运动请注意防晒。推荐您进行室内运动。"},"trav":{"brf":"适宜","txt":"天气较好，但丝毫不会影响您的心情。微风，虽天气稍热，却仍适宜旅游，不要错过机会呦！"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    @SerializedName("HeWeather data service 3.0")
    private List<HeWeatherEntity> HeWeather;

    public List<HeWeatherEntity> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherEntity> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public static class HeWeatherEntity {
        /**
         * city : {"aqi":"114","co":"1","no2":"41","o3":"65","pm10":"69","pm25":"114","qlty":"轻度污染","so2":"9"}
         */

        private AqiEntity aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-06-06 09:51","utc":"2016-06-06 01:51"}
         */

        private BasicEntity basic;
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 25
         * hum : 64
         * pcpn : 0
         * pres : 1011
         * tmp : 23
         * vis : 7
         * wind : {"deg":"91","dir":"东北风","sc":"4-5","spd":"19"}
         */

        private NowEntity now;
        private String status;
        /**
         * comf : {"brf":"较不舒适","txt":"白天天气多云，同时会感到有些热，不很舒适。"}
         * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
         * drsg : {"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
         * sport : {"brf":"较适宜","txt":"天气较好，户外运动请注意防晒。推荐您进行室内运动。"}
         * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您的心情。微风，虽天气稍热，却仍适宜旅游，不要错过机会呦！"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        private SuggestionEntity suggestion;
        /**
         * astro : {"sr":"04:46","ss":"19:40"}
         * cond : {"code_d":"101","code_n":"302","txt_d":"多云","txt_n":"雷阵雨"}
         * date : 2016-06-06
         * hum : 22
         * pcpn : 0.0
         * pop : 8
         * pres : 1007
         * tmp : {"max":"31","min":"20"}
         * vis : 10
         * wind : {"deg":"190","dir":"无持续风向","sc":"微风","spd":"2"}
         */

        private List<DailyForecastEntity> daily_forecast;
        /**
         * date : 2016-06-06 10:00
         * hum : 39
         * pop : 0
         * pres : 1010
         * tmp : 30
         * wind : {"deg":"145","dir":"东南风","sc":"微风","spd":"6"}
         */

        private List<HourlyForecastEntity> hourly_forecast;

        public AqiEntity getAqi() {
            return aqi;
        }

        public void setAqi(AqiEntity aqi) {
            this.aqi = aqi;
        }

        public BasicEntity getBasic() {
            return basic;
        }

        public void setBasic(BasicEntity basic) {
            this.basic = basic;
        }

        public NowEntity getNow() {
            return now;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastEntity> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastEntity> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class AqiEntity {
            /**
             * aqi : 114
             * co : 1
             * no2 : 41
             * o3 : 65
             * pm10 : 69
             * pm25 : 114
             * qlty : 轻度污染
             * so2 : 9
             */

            private CityEntity city;

            public CityEntity getCity() {
                return city;
            }

            public void setCity(CityEntity city) {
                this.city = city;
            }

            public static class CityEntity {
                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicEntity {
            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            /**
             * loc : 2016-06-06 09:51
             * utc : 2016-06-06 01:51
             */

            private UpdateEntity update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateEntity getUpdate() {
                return update;
            }

            public void setUpdate(UpdateEntity update) {
                this.update = update;
            }

            public static class UpdateEntity {
                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowEntity {
            /**
             * code : 101
             * txt : 多云
             */

            private CondEntity cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 91
             * dir : 东北风
             * sc : 4-5
             * spd : 19
             */

            private WindEntity wind;

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class CondEntity {
                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionEntity {
            /**
             * brf : 较不舒适
             * txt : 白天天气多云，同时会感到有些热，不很舒适。
             */

            private ComfEntity comf;
            /**
             * brf : 不宜
             * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
             */

            private CwEntity cw;
            /**
             * brf : 热
             * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
             */

            private DrsgEntity drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             */

            private FluEntity flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，户外运动请注意防晒。推荐您进行室内运动。
             */

            private SportEntity sport;
            /**
             * brf : 适宜
             * txt : 天气较好，但丝毫不会影响您的心情。微风，虽天气稍热，却仍适宜旅游，不要错过机会呦！
             */

            private TravEntity trav;
            /**
             * brf : 中等
             * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
             */

            private UvEntity uv;

            public ComfEntity getComf() {
                return comf;
            }

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public static class ComfEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastEntity {
            /**
             * sr : 04:46
             * ss : 19:40
             */

            private AstroEntity astro;
            /**
             * code_d : 101
             * code_n : 302
             * txt_d : 多云
             * txt_n : 雷阵雨
             */

            private CondEntity cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 31
             * min : 20
             */

            private TmpEntity tmp;
            private String vis;
            /**
             * deg : 190
             * dir : 无持续风向
             * sc : 微风
             * spd : 2
             */

            private WindEntity wind;

            public AstroEntity getAstro() {
                return astro;
            }

            public void setAstro(AstroEntity astro) {
                this.astro = astro;
            }

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class AstroEntity {
                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondEntity {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpEntity {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastEntity {
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            /**
             * deg : 145
             * dir : 东南风
             * sc : 微风
             * spd : 6
             */

            private WindEntity wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
