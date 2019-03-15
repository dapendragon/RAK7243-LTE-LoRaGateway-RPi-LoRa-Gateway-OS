#
# Copy the ppp script files for Quectel chips to the target filesystem
# These files are based on the details provided in 
# Quectel_WCDMA&LTE_Linux_USB_Driver_User_Guide_V1.5.pdf and are shared
# as part of this layer with their permissoin.  9*9*
#

SUMMARY = "PPP Scripts for Basic Quectel Chip Operation"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DESCRIPTION = "A set of Linux PPP scripts that provide connect, disconnect and other basic Point-to-Point Protocol functionality for Quectel wireless modems.  "

RDEPENDS_${PN} = "ppp"

SRC_URI += "file://quectel-ppp"
SRC_URI += "file://quectel-chat-connect"
SRC_URI += "file://quectel-chat-disconnect"
SRC_URI += "file://quectel-ip-up"
SRC_URI += "file://quectel-hat.sh"
SRC_URI += "file://quectel-ppp.monit"
SRC_URI += "file://quectel-ppp.init"

S = "${WORKDIR}"

inherit allarch

# Install script on target's root filesystem
do_install () {
    # Install init script and default settings
    # ${sysconfdir}
    install -d ${D}${sysconfdir}/
    install -d ${D}${sysconfdir}/ppp
    install -d ${D}${sysconfdir}/ppp/peers
    install -m 0755 ${S}/quectel-ppp ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-chat-connect ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-chat-disconnect ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-ip-up ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-hat.sh ${D}${sysconfdir}/ppp/peers/quectel-hat.sh
    install -d ${D}${sysconfdir}/monit.d
    install -m 0644 ${WORKDIR}/quectel-ppp.monit ${D}${sysconfdir}/monit.d/quectel-ppp
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/quectel-ppp.init ${D}${sysconfdir}/init.d/quectel-ppp
}

# Mark the files which are part of this package
FILES_${PN} += "{sysconfdir}/ppp/"
FILES_${PN} += "{sysconfdir}/ppp/peers/"
FILES_${PN} += "{sysconfdir}/ppp/peers/quectel-ppp"
FILES_${PN} += "{sysconfdir}/ppp/peers/quectel-chat-connect"
FILES_${PN} += "{sysconfdir}/ppp/peers/quectel-chat-disconnect"
FILES_${PN} += "{sysconfdir}/ppp/peers/quectel-ip-up"
FILES_${PN} += "{sysconfdir}/ppp/peers/quectel-hat.sh"
FILES_${PN} += "{sysconfdir}/monit.d/"
FILES_${PN} += "{sysconfdir}/monit.d/quectel-ppp"
FILES_${PN} += "{sysconfdir}/init.d/"
FILES_${PN} += "{sysconfdir}/init.d/quectel-ppp"
