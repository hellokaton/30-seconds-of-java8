module=$1

#export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
#export PATH=$JAVA_HOME/bin:$PATH

mvn install -pl ${module} -am -DskipTests -Dfindbugs.skip -Dbasepom.check.skip-prettier -Dgpg.skip \
    -Drat.skip -Dskip.npm -Dskip.yarn -Dskip.bower -Dskip.grunt -Dskip.gulp -Dskip.jspm -Dskip.karma \
    -Dskip.webpack -Dcheckstyle.skip -Denforcer.skip -Dspotbugs.skip \
    -Dmaven.test.failure.ignore -Djacoco.skip -Danimal.sniffer.skip \
    -Dmaven.antrun.skip -Dfmt.skip -Dskip.npm -Dlicense.skipCheckLicense -Denforcer.skip \
    -DskipDockerBuild -DskipDockerTag -DskipDockerPush -DskipDocker  -Dlicense.skipAddThirdParty \
    -Dfindbugs.skip -Dlicense.skip |&tee install.log
