# -*- coding: utf-8 -*-
#import csv
import os
import fnmatch
import collections
import json
import numpy as np
from scipy import stats

import ipdb as pdb

#definindo path para o documento (não sei se é necessário)
#criando arquivo pra receber os resultados (definir formato do arquigo)
#carregar arquivo destino

#FAZENDO AQUI

#carregando relatorio e diretorio
result = open("result.txt", "w")
print ("diretorio origem", os.getcwd())

#result.write("mean exeTime(ms),median exeTime(ms),mode exeTime(ms),std exeTime(ms), testSize,resul(1=pass,0=fail,-1=abort)") 

#buscando arquivos com dados
CSVFiles = []
files = os.listdir(os.getcwd())
for dirFile in files:
    if dirFile.endswith('.csv'):
        #arquivos CSV são armazenados para serem processados a seguir
        CSVFiles.append(dirFile)

#Arquivos CSV carregados vamos ao loop de processesamento
for csv in CSVFiles:
    if not csv.startswith( 'result' ):
        print(csv)
        exeTime, testSize, testResult = np.loadtxt(csv,delimiter=",", unpack=True)
        #fazendo os calculos  EXETIME
        meanEXE = np.mean(exeTime)
        medianEXE = np.median(exeTime)
        modeEXE = stats.mode(exeTime)
        stdEXE = np.std(exeTime)
        #fazendo os calculos  testSize
        meantestSize = np.mean(testSize)
        mediantestSize = np.median(testSize)
        modetestSize = stats.mode(testSize)
        stdtestSize = np.std(testSize)
        #fazendo a contagem de sucessos, falhas e erros
        rsltZ = collections.Counter(testResult)

        #pdb.set_trace()

        # convert to string
        #input = json.dumps({'id': id })
        strZ = str(dict(rsltZ))
        #   load to dict
        #my_dict = json.loads(input)    

        #Escrevendo resultado no arquivo de relatorio
        #result.write(str(meanEXE)+","+str(medianEXE)","+str(modeEXE)","+str(stdEXE)","+str(meantestSize)","+str(mediantestSize)","+str(modetestSize)","+str(stdtestSize)","+listResult) 
        txt = "{0:0.2f},{1:0.2f},{2:0.2f},{3:0.2f},{4:0.2f},{5:0.2f},{6:0.2f},{7:0.2f}".format(meanEXE, medianEXE, modeEXE.mode[0], stdEXE, meantestSize, mediantestSize, modetestSize.mode[0], stdtestSize)

        txt += ",{0:d},{1:d},{2:d}\n".format(rsltZ[0], rsltZ[1], rsltZ[-1])


        #txt = ''.join([str(meanEXE), str(medianEXE), str(modeEXE),str(stdEXE),str(meantestSize),str(mediantestSize),str(modetestSize),str(stdtestSize),strZ])
        
        result.write(txt)

result.close()