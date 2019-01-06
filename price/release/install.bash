#!/usr/bin/env bash
echo 'adb uninstall com.abt.price'
adb uninstall com.abt.price
echo 'apk uninstalled'

echo 'adb install'
adb install /Users/huangweiqi/StudioProjects/April/release/Price/Price_V1.0.0_20180610_B.apk
echo 'apk installed'
