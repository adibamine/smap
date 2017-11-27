#!/bin/bash
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color
declare -A simulations
declare -A configs
simulations=( 
	["ApiConfig"]="DURATION RAMP_CONFIG_USERS SCN_CONFIG_PAUSE" 
	["ApiDiscovery"]="RAMP_DISCOVERY_USERS DISCOVERY_DURATION SCN_DISCOVERY_PAUSE"
	["ExternalBorj"]="BASE_EXTERNAL_BORJ_URL RAMP_USERS USER_INJECTION_DURATION SCN_DURATION_BORJ_REST SCN_EXTERNAL_BORJ_PAUSE"
	["ExternalHost"]="SERVER HTTP_PORT RAMP_HOST_USERS HOST_DURATION SCN_HOST_EQUIPEMENTS_PAUSE"
)
configs=( 
	["DURATION"]="10" ["RAMP_CONFIG_USERS"]="20" ["SCN_CONFIG_PAUSE"]="30" ["RAMP_DISCOVERY_USERS"]="40" ["DISCOVERY_DURATION"]="50" ["SCN_DISCOVERY_PAUSE"]="60"
	["BASE_EXTERNAL_BORJ_URL"]="https://172.29.83.50" ["USER_INJECTION_DURATION"]="100" ["SCN_EXTERNAL_BORJ_PAUSE"]="20" ["RAMP_USERS"]="100" ["SCN_DURATION_BORJ_REST"]="10"
	["SERVER"]="apiprd.attijariwafa.net" ["HTTP_PORT"]="443" ["RAMP_HOST_USERS"]="apiprd.attijariwafa.net" ["HOST_DURATION"]="10" ["SCN_HOST_EQUIPEMENTS_PAUSE"]="4"
)

echo "////////////////////////////////////////"
for simulation in "${!simulations[@]}"; do echo "             - $simulation"; done
echo "////////////////////////////////////////"

while true; do 
    echo "Choisissez une simulation(ex: ApiDiscovery): "
	read CURRENT_SIMULATION
	if [ -z "$CURRENT_SIMULATION" ]; then
		echo -e "${RED}(!) Saisie invalide${NC}"
	elif [ -n "${simulations["$CURRENT_SIMULATION"]}" ]; then
		break
	else
		echo -e "${RED}(!) $CURRENT_SIMULATION n'existe pas.${NC}"
	fi
done

PARAMVALUE="-Dgatling.simulationClass=ma.octo.simulations.$CURRENT_SIMULATION"
echo "************************************"
echo " - liste des configs pour $CURRENT_SIMULATION: "
echo "-> ${simulations[$CURRENT_SIMULATION]}"
echo "************************************"
echo " - Début configuration simulation: "

for config in ${simulations[$CURRENT_SIMULATION]}
do
   echo "$config(${configs[$config]}): "
   read TMP_PARAMVALUE
   if [ -z "$TMP_PARAMVALUE" ]; then
   	PARAMVALUE="$PARAMVALUE -D$config=${configs[$config]}"
   else
   	PARAMVALUE="$PARAMVALUE -D$config=$TMP_PARAMVALUE"
   fi	
done
echo "************************************"
SCRIPT="mvn gatling:execute $PARAMVALUE"
echo -e "${GREEN}$SCRIPT${NC}"
eval $SCRIPT
