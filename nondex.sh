module=$1
test=$2

#export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
#export PATH=$JAVA_HOME/bin:$PATH

mvn edu.illinois:nondex-maven-plugin:2.1.1:nondex -pl ${module} -Dtest=${test}  -Dbasepom.check.skip-prettier -Dgpg.skip -Dfindbugs.skip=true \
    -Drat.skip -Dcheckstyle.skip -Denforcer.skip -Dspotbugs.skip -Dmaven.test.failure.ignore \
    -Djacoco.skip -Danimal.sniffer.skip -Dmaven.antrun.skip -Dfmt.skip -Dskip.npm -Dlicense.skipCheckLicense \
    -Dlicense.skipAddThirdParty -Dfindbugs.skip -Dlicense.skip -DskipDockerBuild -DskipDockerTag \
    -DskipDockerPush -DskipDocker  -Dskip.npm -Dskip.yarn -Dskip.bower \
    -Dskip.grunt -Dskip.gulp -Dskip.jspm -Dskip.karma -Dskip.webpack -DnondexRuns=10 |&tee n1.log
