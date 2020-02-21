from app import app
from app import contract_interface as ci
from flask import request
import json
from random import randint
web3=ci.web3

ContractInfo=ci.readContractInfo(r'app\compiled\contracts\contract.json')
abi=ContractInfo['Campaign']['abi']


@app.route('/checkconnectivity/',methods=['GET'])
def connectivity():
    print((web3.isConnected()))
    return json.dumps({'response':(web3.isConnected())})

# @app.route('/openFundBucket',methods=['POST'])
# def CreateNewBucket():
#     try:
#         name=request.json['name']
#         account=request.json['account']
#         owner=ci.CreateFunder(name,acc=account)
#         contract_Funder=ci.createContract(FunderContract,'Funders',owner.getAcc())
#         return json.dumps({'response':True})
#     except :
#         return json.dumps({'response':False})
        

@app.route('/registerCampaign',methods=['POST'])
def CreateCampaign():
    try:
        name=request.json['name']
        account=request.json['account']
        FundSeeker=ci.CreateFundSeeker(name,acc=account)
        contract_FundSeeker=ci.createContract(ContractInfo,'Campaign',FundSeeker.getAcc())
        print(contract_FundSeeker.address)
        return json.dumps({'response':True,'name':name,'address':account,'account':contract_FundSeeker.address})
    except Exception as e:
        print("error is ")
        print(e)
        return json.dumps({'response':False})

# @app.route('/registerFunder')
# def CreateFunderForBucket():
#         name=request.json['name']
#         account=request.json['account']
#         Funder=ci.CreateFunder(name,acc=account)
#         user=ci.registerFunder(Contract,Funder)
#         return json.dumps({'response':True})

@app.route('/registerFunder',methods=['POST'])
def registerFunder():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    tx=ci.registerFunder(Contract,funder=Funder)
    return json.dumps({'response':True})
 
@app.route('/getFundSeeker',methods=['POST'])
def getFundSeeker():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    fundSeeker=ci.FundSeeker(name,account)
    Contract=retreiveContract(ContractAdd)
    tx=ci.getFundSeeker(Contract,fundSeeker)
    return json.dumps({'response':True,'description':str(tx)})
@app.route('/sendMoneyToFundSeeker',methods=['POST'])
def sendMoneyToFundSeeker():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    value=request.json['value']
    Contract=retreiveContract(ContractAdd)
    print(value)
    Funder=ci.CreateFunder(name,acc=account)
    [tx,val]=ci.DonateMoney(Contract,Funder,value)
    return json.dumps({'response':True,'tx':str(tx),'val':str(val)})

@app.route('/startVotingFor',methods=['POST'])
def startVotingFor():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    ci.startVotingFor(Contract,Funder.getAcc())
    return json.dumps({'response':True})

@app.route('/endVotingFor',methods=['POST'])
def endVotingFor():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    ci.endVotingFor(Contract,Funder.getAcc())
    return json.dumps({'response':True})

@app.route('/voteFor',methods=['POST'])
def vote():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    vote=request.json['vote']
    ci.voteFor(Contract,Funder,vote)
    return json.dumps({'response':True})



@app.route('/isAllowedToWithDraw',methods=['GET'])
def isAllowedToWithDraw():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    p=ci.isAllowedToWithDraw(Contract,Funder)
    return{'response':True,'body':str(p)}

@app.route('/getCurrentFundingStageFor',methods=['GET'])
def getCurrentFundingStageFor():
    name=request.json['names']
    account=request.json['account']
    ContractAdd=request.json['contract']
    Contract=retreiveContract(ContractAdd)
    Funder=ci.CreateFunder(name,acc=account)
    p=ci.getCurrentFundingStageFor(Contract,Funder)
    return json.dumps({
        'response':True,
        'Stage':p
    })

@app.route('/getRandomAddress',methods=['GET'])
def getRandAddress():
    i=randint(0,len(web3.eth.accounts)-1)
    print(web3.eth.accounts[i])
    return json.dumps({'response':True,'account':web3.eth.accounts[i]})

def retreiveContract(ContractAddress):
    return ci.retreiveContract(ContractAddress,abi)
