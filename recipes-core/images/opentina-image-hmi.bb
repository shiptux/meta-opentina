# SPDX-License-Identifier: MIT
SUMMARY = "OpenTina Wayland/Weston HMI rootfs for Allwinner A733"
DESCRIPTION = "Wayland + Weston compositor, XWayland, Mesa with the imagination \
Vulkan driver (PowerVR BXM-4-64) and zink, GStreamer, and PulseAudio for \
display/HMI bring-up. Chromium is deferred (see TODO below)."

inherit core-image
inherit opentina-default-users

IMAGE_FEATURES += " \
    splash \
    package-management \
    ssh-server-openssh \
    allow-root-login \
"

IMAGE_INSTALL += " \
    packagegroup-core-boot \
    packagegroup-core-tools-debug \
    openssh-sftp-server \
    packagegroup-opentina-graphics \
    packagegroup-opentina-multimedia \
    packagegroup-opentina-audio \
    packagegroup-opentina-x11 \
    packagegroup-opentina-tools \
    packagegroup-opentina-net \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd systemd-serialgetty', 'sysvinit sysvinit-inittab', d)} \
"

set_pvr_env() {
    printf '%s\n' 'PVR_I_WANT_A_BROKEN_VULKAN_DRIVER=1' \
        >> ${IMAGE_ROOTFS}${sysconfdir}/environment
}
ROOTFS_POSTPROCESS_COMMAND += "set_pvr_env;"

# weston comes in through IMAGE_INSTALL, not the "weston" IMAGE_FEATURE, so
# rootfs-postcommands would leave default.target at multi-user and neither
# weston.service nor opentina-kiosk.service (both WantedBy=graphical.target)
# would start at boot.
SYSTEMD_DEFAULT_TARGET = "graphical.target"

export IMAGE_BASENAME = "opentina-image-hmi"

IMAGE_ROOTFS_SIZE ?= "524288"

# TODO: Chromium (Ozone/Wayland) is deferred. It needs meta-browser +
# meta-clang layers and a multi-hour build; add chromium-ozone-wayland to
# IMAGE_INSTALL once those layers are wired into bblayers.conf.
