package com.living.bean;

import java.util.List;

/**
 * Author：燕青 $ on 16/4/18 12:01
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class CountryWeatherBean extends NetModel{


    /**
     * aqi : {"city":{"aqi":"33","co":"0","no2":"7","o3":"103","pm10":"19","pm25":"22","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-04-18 15:49","utc":"2016-04-18 07:49"}}
     * daily_forecast : [{"astro":{"sr":"05:31","ss":"18:55"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-04-18","hum":"8","pcpn":"0.0","pop":"1","pres":"1013","tmp":{"max":"21","min":"8"},"vis":"10","wind":{"deg":"316","dir":"北风","sc":"3-4","spd":"12"}},{"astro":{"sr":"05:30","ss":"18:56"},"cond":{"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"},"date":"2016-04-19","hum":"20","pcpn":"1.3","pop":"84","pres":"1015","tmp":{"max":"16","min":"9"},"vis":"10","wind":{"deg":"168","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"05:28","ss":"18:57"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-04-20","hum":"12","pcpn":"0.0","pop":"16","pres":"1011","tmp":{"max":"26","min":"12"},"vis":"10","wind":{"deg":"26","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:27","ss":"18:58"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-04-21","hum":"11","pcpn":"0.0","pop":"0","pres":"1003","tmp":{"max":"29","min":"15"},"vis":"10","wind":{"deg":"308","dir":"北风","sc":"3-4","spd":"10"}},{"astro":{"sr":"05:25","ss":"18:59"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-04-22","hum":"11","pcpn":"0.0","pop":"0","pres":"1009","tmp":{"max":"24","min":"11"},"vis":"10","wind":{"deg":"307","dir":"北风","sc":"3-4","spd":"16"}},{"astro":{"sr":"05:24","ss":"19:00"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-04-23","hum":"7","pcpn":"0.0","pop":"0","pres":"1012","tmp":{"max":"23","min":"11"},"vis":"10","wind":{"deg":"291","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"05:23","ss":"19:01"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-04-24","hum":"15","pcpn":"0.2","pop":"42","pres":"1010","tmp":{"max":"26","min":"13"},"vis":"10","wind":{"deg":"201","dir":"无持续风向","sc":"微风","spd":"0"}}]
     * hourly_forecast : [{"date":"2016-04-18 16:00","hum":"8","pop":"0","pres":"1012","tmp":"21","wind":{"deg":"304","dir":"西北风","sc":"3-4","spd":"21"}},{"date":"2016-04-18 19:00","hum":"12","pop":"0","pres":"1013","tmp":"20","wind":{"deg":"247","dir":"西南风","sc":"微风","spd":"10"}},{"date":"2016-04-18 22:00","hum":"14","pop":"0","pres":"1015","tmp":"17","wind":{"deg":"211","dir":"西南风","sc":"微风","spd":"8"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"21","hum":"11","pcpn":"0","pres":"1012","tmp":"19","vis":"10","wind":{"deg":"340","dir":"北风","sc":"5-6","spd":"32"}}
     * status : ok
     * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    private List<HeWeatherEntity> HeWeather;

    public void setHeWeather(List<HeWeatherEntity> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public List<HeWeatherEntity> getHeWeather() {
        return HeWeather;
    }

    public static class HeWeatherEntity {
        /**
         * city : {"aqi":"33","co":"0","no2":"7","o3":"103","pm10":"19","pm25":"22","qlty":"优","so2":"2"}
         */

        private AqiEntity aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-04-18 15:49","utc":"2016-04-18 07:49"}
         */

        private BasicEntity basic;
        /**
         * cond : {"code":"100","txt":"晴"}
         * fl : 21
         * hum : 11
         * pcpn : 0
         * pres : 1012
         * tmp : 19
         * vis : 10
         * wind : {"deg":"340","dir":"北风","sc":"5-6","spd":"32"}
         */

        private NowEntity now;
        private String status;
        /**
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。"}
         * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        private SuggestionEntity suggestion;
        /**
         * astro : {"sr":"05:31","ss":"18:55"}
         * cond : {"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"}
         * date : 2016-04-18
         * hum : 8
         * pcpn : 0.0
         * pop : 1
         * pres : 1013
         * tmp : {"max":"21","min":"8"}
         * vis : 10
         * wind : {"deg":"316","dir":"北风","sc":"3-4","spd":"12"}
         */

        private List<DailyForecastEntity> daily_forecast;
        /**
         * date : 2016-04-18 16:00
         * hum : 8
         * pop : 0
         * pres : 1012
         * tmp : 21
         * wind : {"deg":"304","dir":"西北风","sc":"3-4","spd":"21"}
         */

        private List<HourlyForecastEntity> hourly_forecast;

        public void setAqi(AqiEntity aqi) {
            this.aqi = aqi;
        }

        public void setBasic(BasicEntity basic) {
            this.basic = basic;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public AqiEntity getAqi() {
            return aqi;
        }

        public BasicEntity getBasic() {
            return basic;
        }

        public NowEntity getNow() {
            return now;
        }

        public String getStatus() {
            return status;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public List<DailyForecastEntity> getDaily_forecast() {
            return daily_forecast;
        }

        public List<HourlyForecastEntity> getHourly_forecast() {
            return hourly_forecast;
        }

        public static class AqiEntity {
            /**
             * aqi : 33
             * co : 0
             * no2 : 7
             * o3 : 103
             * pm10 : 19
             * pm25 : 22
             * qlty : 优
             * so2 : 2
             */

            private CityEntity city;

            public void setCity(CityEntity city) {
                this.city = city;
            }

            public CityEntity getCity() {
                return city;
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

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getAqi() {
                    return aqi;
                }

                public String getCo() {
                    return co;
                }

                public String getNo2() {
                    return no2;
                }

                public String getO3() {
                    return o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public String getSo2() {
                    return so2;
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
             * loc : 2016-04-18 15:49
             * utc : 2016-04-18 07:49
             */

            private UpdateEntity update;

            public void setCity(String city) {
                this.city = city;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public void setUpdate(UpdateEntity update) {
                this.update = update;
            }

            public String getCity() {
                return city;
            }

            public String getCnty() {
                return cnty;
            }

            public String getId() {
                return id;
            }

            public String getLat() {
                return lat;
            }

            public String getLon() {
                return lon;
            }

            public UpdateEntity getUpdate() {
                return update;
            }

            public static class UpdateEntity {
                private String loc;
                private String utc;

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }

                public String getLoc() {
                    return loc;
                }

                public String getUtc() {
                    return utc;
                }
            }
        }

        public static class NowEntity {
            /**
             * code : 100
             * txt : 晴
             */

            private CondEntity cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 340
             * dir : 北风
             * sc : 5-6
             * spd : 32
             */

            private WindEntity wind;

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getFl() {
                return fl;
            }

            public String getHum() {
                return hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public String getPres() {
                return pres;
            }

            public String getTmp() {
                return tmp;
            }

            public String getVis() {
                return vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class CondEntity {
                private String code;
                private String txt;

                public void setCode(String code) {
                    this.code = code;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getCode() {
                    return code;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }
            }
        }

        public static class SuggestionEntity {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            private ComfEntity comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private CwEntity cw;
            /**
             * brf : 较舒适
             * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             */

            private DrsgEntity drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            private FluEntity flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。
             */

            private SportEntity sport;
            /**
             * brf : 适宜
             * txt : 天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。
             */

            private TravEntity trav;
            /**
             * brf : 中等
             * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
             */

            private UvEntity uv;

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public ComfEntity getComf() {
                return comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public static class ComfEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class CwEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class DrsgEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class FluEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class SportEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class TravEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class UvEntity {
                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }
        }

        public static class DailyForecastEntity {
            /**
             * sr : 05:31
             * ss : 18:55
             */

            private AstroEntity astro;
            /**
             * code_d : 100
             * code_n : 104
             * txt_d : 晴
             * txt_n : 阴
             */

            private CondEntity cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 21
             * min : 8
             */

            private TmpEntity tmp;
            private String vis;
            /**
             * deg : 316
             * dir : 北风
             * sc : 3-4
             * spd : 12
             */

            private WindEntity wind;

            public void setAstro(AstroEntity astro) {
                this.astro = astro;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public AstroEntity getAstro() {
                return astro;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getDate() {
                return date;
            }

            public String getHum() {
                return hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public String getPop() {
                return pop;
            }

            public String getPres() {
                return pres;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public String getVis() {
                return vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class AstroEntity {
                private String sr;
                private String ss;

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }

                public String getSr() {
                    return sr;
                }

                public String getSs() {
                    return ss;
                }
            }

            public static class CondEntity {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }

                public String getCode_d() {
                    return code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }
            }

            public static class TmpEntity {
                private String max;
                private String min;

                public void setMax(String max) {
                    this.max = max;
                }

                public void setMin(String min) {
                    this.min = min;
                }

                public String getMax() {
                    return max;
                }

                public String getMin() {
                    return min;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
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
             * deg : 304
             * dir : 西北风
             * sc : 3-4
             * spd : 21
             */

            private WindEntity wind;

            public void setDate(String date) {
                this.date = date;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public String getDate() {
                return date;
            }

            public String getHum() {
                return hum;
            }

            public String getPop() {
                return pop;
            }

            public String getPres() {
                return pres;
            }

            public String getTmp() {
                return tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }
            }
        }
    }
}
