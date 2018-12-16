package com.chikage.framework.quartzframework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;


@Component
public class ApplicationContextWare implements ApplicationContextAware {
    private static ApplicationContext context;
    private static boolean romePresent = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", ApplicationContextWare.class.getClassLoader());

    public enum profiles {
        TEST("test"), PRO("pro"), DEFAULT("default"), MOCK("mock");
        private String profile;

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        profiles(String profile) {
            this.profile = profile;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static boolean isTestProfile() {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        if (activeProfiles != null && activeProfiles.length > 0) {
            for (String profile : activeProfiles) {
                if (ApplicationContextWare.profiles.TEST.getProfile().equals(profile)) {
                    return true;
                }

            }
        }
        return false;
    }

    public static boolean isProProfile() {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        if (activeProfiles != null && activeProfiles.length > 0) {
            for (String profile : activeProfiles) {
                if (ApplicationContextWare.profiles.PRO.getProfile().equals(profile)) {
                    return true;
                }

            }
        }
        return false;
    }

    public static String getAppName() {

        return context.getEnvironment().getProperty("spring.application.name");
    }

    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }

    public static <T> T getBean(String var1, Class<T> var2) {
        return context.getBean(var1, var2);
    }

    public static Object getBean(String idOrName) {
        return context.getBean(idOrName);
    }

    /**
     *
     * @param idOrName
     * @return
     */
    public static boolean containsBean(String idOrName){
        return context.containsBean(idOrName);
    }


    public static String getProfile() {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        if (activeProfiles != null && activeProfiles.length > 0) {
            for (String profile : activeProfiles) {
                if (ApplicationContextWare.profiles.PRO.getProfile().equals(profile)) {
                    return ApplicationContextWare.profiles.PRO.getProfile();
                } else if (profiles.TEST.getProfile().equals(profile)) {
                    return ApplicationContextWare.profiles.TEST.getProfile();
                }

            }
        }
        return "";//默认环境返回空字符串
    }

}
