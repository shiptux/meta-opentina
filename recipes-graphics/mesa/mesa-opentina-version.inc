# SPDX-License-Identifier: MIT
# wrynose ships mesa 26.0.5, where zink cannot set up an EGL device on the
# imagination driver ("failed to get driver name for fd -1") and every GL
# client, weston included, falls back to softpipe. 26.1 fixes it; the A733 is
# matched by driver name there, so no device-table patch is needed any more.
# Shared by mesa and mesa-tools-native so the pco_clc precompiler matches.
PV = "26.1.1"
SRC_URI[sha256sum] = "8bd36c031cc6d0edfec04617527609454ee3a09ad53bdf983b45fc2c1e129b2e"
