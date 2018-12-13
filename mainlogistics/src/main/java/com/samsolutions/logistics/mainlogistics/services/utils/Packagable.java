package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

@Component
public interface Packagable {
    default String getPackagePath(PackageType packageType){
        Package[] packages=Package.getPackages();
        if(PackageType.REPOSITORIES_PACKAGE==packageType)
            for (int i=0;i<packages.length;i++)
                if(packages[i].getName().contains("repositories"))
                    return packages[i].getName();
        return null;
    }
}
