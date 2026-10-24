SUMMARY = "PowerVR Rogue firmware for Allwinner A733"
HOMEPAGE = "https://gitlab.freedesktop.org/imagination/linux-firmware"
LICENSE = "Firmware-powervr"
NO_GENERIC_LICENSE[Firmware-powervr] = "LICENSE.powervr"
LIC_FILES_CHKSUM = "file://LICENSE.powervr;md5=83045ed2a2cda15b4eaff682c98c9533"

FW_BASE = "https://gitlab.freedesktop.org/imagination/linux-firmware/-/raw/powervr"
FW_NAME = "rogue_36.56.104.183_v1.fw"

SRC_URI = " \
    ${FW_BASE}/powervr/${FW_NAME};name=fw;downloadfilename=${FW_NAME} \
    ${FW_BASE}/LICENSE.powervr;name=license;downloadfilename=LICENSE.powervr \
"
SRC_URI[fw.sha256sum] = "1db1c399c17401d1f79d46c880db81c724d748c784d4639433b076aba2f9c0d2"
SRC_URI[license.sha256sum] = "1c9aa6bd6703a7ce1cdb879542fa1d8aca115a327bd819b193c971de9c53f402"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/powervr
    install -m 0644 ${UNPACKDIR}/${FW_NAME} ${D}${nonarch_base_libdir}/firmware/powervr/
    install -m 0644 ${UNPACKDIR}/LICENSE.powervr ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/"
INSANE_SKIP:${PN} += "arch"
