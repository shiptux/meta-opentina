SUMMARY = "OpenTina A733 component groups"
DESCRIPTION = "Component-level package groups (GPU/Wayland, GStreamer, \
PulseAudio, X11, test tools, networking) so that images and other layers pull \
a stack by name instead of repeating package lists."
LICENSE = "MIT"

inherit packagegroup

PACKAGES = " \
    ${PN}-graphics \
    ${PN}-multimedia \
    ${PN}-audio \
    ${PN}-x11 \
    ${PN}-tools \
    ${PN}-net \
"

# Wayland compositor and Mesa with the imagination Vulkan driver (PowerVR
# BXM-4-64) plus zink for GL; the kernel powervr module comes from the BSP.
# Libraries (libinput, fontconfig) come in through weston; an allarch
# packagegroup must not name debian-renamed library packages.
RDEPENDS:${PN}-graphics = " \
    weston weston-init wayland \
    mesa mesa-megadriver \
    powervr-firmware-a733 \
    liberation-fonts \
"

RDEPENDS:${PN}-multimedia = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good gstreamer1.0-plugins-good-vpx \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-libav \
"

RDEPENDS:${PN}-audio = " \
    pulseaudio pulseaudio-server alsa-utils \
"

# XWayland is started on demand by weston; the clients are for smoke tests.
RDEPENDS:${PN}-x11 = " \
    xwayland xauth xhost xdpyinfo xev \
"

RDEPENDS:${PN}-tools = " \
    mesa-demos kmscube libdrm-tests wayland-utils \
    vulkan-tools glmark2 weston-examples evtest \
    opentina-hmi-tools \
"

RDEPENDS:${PN}-net = " \
    iproute2 iproute2-ss iputils ethtool wpa-supplicant iw \
"
