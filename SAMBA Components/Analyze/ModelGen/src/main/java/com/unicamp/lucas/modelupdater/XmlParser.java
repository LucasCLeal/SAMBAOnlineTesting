/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.unicamp.lucas.common.pojo.Edge;

/**
 *
 * @author LucasCLeal
 */
public class XmlParser {

    //array que recebera as sesenquancias de operações listadas dentro do arquivo BPEL
    static ArrayList<ArrayList<ArrayList<Edge>>> sqnc = new ArrayList<>();

    public static Document parseFile(File file) {
        //criando document builder factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        System.out.println("[XmlParser] bpelfileName - " + file.getName());
        try {
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e);
            return null;
        }

    }

    public ArrayList<String> getBPELPartnerLinks(Document document) {

        ArrayList<String> partners = new ArrayList<>();
        //primeiro elemento do BPEL file process
        NodeList rootNodes = document.getChildNodes();
        for (int i = 0; i < rootNodes.getLength(); i++) {
            //varrendo todos os nós
            Node rootNode = rootNodes.item(i);
            //verificando se nó é do tipo elemento
            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {

                //verificando se o nome do elemento se chama process
                if (rootNode.getNodeName().equals("process")) {
                    //criando um Element para no
                    Element process = (Element) rootNode;
                    System.out.println("[XmlParser]: BPEL Filename: " + process.getAttribute("name"));
                    //obtendo nodes do elemento
                    NodeList processNodes = process.getChildNodes();
                    //varrendo todos os nós
                    for (int x = 0; x < processNodes.getLength(); x++) {
                        Node processNode = processNodes.item(x);
                        //verificando o tipo do elemento
                        //verificando se nó é do tipo elemento
                        if (processNode.getNodeType() == Node.ELEMENT_NODE) {
                            //verificando se o nome do elemento se chama process
                            if (processNode.getNodeName().equals("partnerLinks")) {
                                //criando um Element para no
                                Element partnerLinks = (Element) processNode;
                                //VARRENDO TODOS OS NÓS MAIS UMA VEZ PARA OBTER OS DADOS DOS PARCEIROS!!!!
                                //obtendo nodes do elemento
                                NodeList partnerLinksNodes = partnerLinks.getChildNodes();
                                //varrendo todos os nós
                                for (int y = 0; y < partnerLinksNodes.getLength(); y++) {
                                    Node partnerLinksNode = partnerLinksNodes.item(y);
                                    //verificando o tipo do elemento
                                    //verificando se nó é do tipo elemento
                                    if (partnerLinksNode.getNodeType() == Node.ELEMENT_NODE) {
                                        //verificando se o nome do elemento se chama process
                                        if (partnerLinksNode.getNodeName().equals("partnerLink")) {
                                            //criando um Element para no
                                            Element partner = (Element) partnerLinksNode;
                                            //FILMENTE OBTENDO OS NOME DO PARCEIRO ADICIONANDO NA LISTA
                                            partners.add(partner.getAttribute("name"));

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return partners;
    }

    public ArrayList<ArrayList<ArrayList<Edge>>> getBPELSequence(Document document) {

        //limpando sequencia antes de começar processo.
        sqnc.clear();
        //lendo os nós da raiz
        NodeList rootNodes = document.getChildNodes();
        for (int i = 0; i < rootNodes.getLength(); i++) {
            //varrendo todos os nós
            Node rootNode = rootNodes.item(i);
            //verificando se nó é do tipo elemento
            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                //procurando o nó Proccess
                if (rootNode.getNodeName().equals("process")) {
                    //criando um Element para ler conteudo do nó
                    Element process = (Element) rootNode;
                    System.out.println("[XmlParser] BPEL Filename: " + process.getAttribute("name"));
                    //obtendo nodes do elemento
                    NodeList processNodes = process.getChildNodes();
                    //varrendo todos os nós
                    for (int y = 0; y < processNodes.getLength(); y++) {
                        Node processNode = processNodes.item(y);
                        //verificando se nó é do tipo elemento
                        if (processNode.getNodeType() == Node.ELEMENT_NODE) {
                            if (processNode.getNodeName().equals("sequence")) {
                                //criando um elemento para ler o conteudo do nó
                                Element sequence = (Element) processNode;
                                //obtendo nós
                                NodeList sequenceNodes = sequence.getChildNodes();
                                //varrendo lista de nós
                                for (int x = 0; x < sequenceNodes.getLength(); x++) {
                                    //dependendo do tipo de nó tem um tratamento diferrente
                                    Node sequenceNode = sequenceNodes.item(x);
                                    //verificando se é do tipo ELement_node
                                    if (sequenceNode.getNodeType() == Node.ELEMENT_NODE) {

                                        //caso seja scope
                                        if (sequenceNode.getNodeName().equals("scope")) {
                                            //criando arraytemp
                                            ArrayList<Edge> inner = new ArrayList<>();
                                            //buscando informações dentro da TAGS
                                            System.out.println("[XmlParser] Adding scope");
                                            Edge edg = readBpelScopeTag(sequenceNode);
                                            //verificando se existe conteudo para ser adicionado                                            
                                            if (!edg.getName().equals("dummy")) {
                                                inner.add(edg);
                                                // adicionando ao array de resposta 
                                                ArrayList<ArrayList<Edge>> outer = new ArrayList<>();
                                                //adicionando a array de saida
                                                outer.add(inner);
                                                sqnc.add(outer);
                                            }
                                        }

                                        //caso seja flow
                                        if (sequenceNode.getNodeName().equals("flow")) {
                                            //buscando informações de TAGS de scope dentro do FLOW
                                            System.out.println("[XmlParser] Adding flow");
                                            sqnc.add(readBpelFlowTag(sequenceNode));
                                        }

                                        //caso seja sequence
                                        if (sequenceNode.getNodeName().equals("sequence")) {
                                            //criando arraytemp
                                            ArrayList<Edge> inner = new ArrayList<>();
                                            //buscando informações dentro da TAGS
                                            System.out.println("[XmlParser] Adding sequence");
                                            Edge edg = readBpelSequenceTag(sequenceNode);
                                            //verificando se existe conteudo para ser adicionado
                                            System.out.println("[XmlParser] Adding sequence: " + edg.toString());
                                            if (!edg.getName().equals("dummy")) {
                                                inner.add(edg);
                                                // adicionando ao array de resposta 
                                                ArrayList<ArrayList<Edge>> outer = new ArrayList<>();
                                                //adicionando a array de saida
                                                outer.add(inner);
                                                sqnc.add(outer);
                                            }
                                        }

                                        //caso seja IF
                                        if (sequenceNode.getNodeName().equals("if")) {
                                            System.out.println("[XmlParser] Adding if");
                                            sqnc.add(readBpelIfTag(sequenceNode));
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sqnc);
        return sqnc;
    }

    public Edge readBpelScopeTag(Node sqNode) {

        Edge operationInfo = new Edge();
        //criando um Element para varrer tags dentro <scope>
        Element scope = (Element) sqNode;
        //VARRENDO TODOS OS NÓS MAIS UMA VEZ PARA OBTER OS DADOS da operação executada!!!!
        //obtendo nodes do elemento
        NodeList scopeNodes = scope.getChildNodes();
        //varrendo todos os nós

        for (int x = 0; x < scopeNodes.getLength(); x++) {
            Node scopeNode = scopeNodes.item(x);
            //verificando o tipo do elemento
            //verificando se nó é do tipo elemento

            if (scopeNode.getNodeType() == Node.ELEMENT_NODE) {
                //verificando se o nome do elemento se chama sequence
                if (scopeNode.getNodeName().equals("sequence")) {
                    operationInfo = readBpelSequenceTag(scopeNode);
                }
            }
        }

        if (operationInfo.getName() == null || operationInfo.getName().equals("")) {
            operationInfo.setName("dummy");
        }
        return operationInfo;
    }

    public ArrayList<ArrayList<Edge>> readBpelIfTag(Node sqNode) {

        ArrayList<ArrayList<Edge>> ifEdges = new ArrayList<>();
        //varrendo as sequencias dentro do flow, cada sequencia é representada por um array
        //criando um Element para varrer tags dentro <scope>
        Element ifElement = (Element) sqNode;
        //obtendo nodes do elemento
        NodeList ifNodes = ifElement.getChildNodes();
        //varrendo todos os nós

        for (int x = 0; x < ifNodes.getLength(); x++) {
            Node ifNode = ifNodes.item(x);
            //verificando o tipo do elemento
            //verificando se nó é do tipo elemento
            if (ifNode.getNodeType() == Node.ELEMENT_NODE) {

                //verificando se o nome do elemento se chama sequence
                if (ifNode.getNodeName().equals("sequence")) {
                    //criando um array para receber os dados das tags <scope> dentro da sequencia
                    ArrayList<Edge> tempArraySeq = new ArrayList<>();
                    //convertendo node em element para acessar mais tags.
                    Element ifSequence = (Element) ifNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList ifSequenceNodes = ifSequence.getChildNodes();
                    for (int y = 0; y < ifSequenceNodes.getLength(); y++) {
                        Node flowSequenceNode = ifSequenceNodes.item(y);
                        if (flowSequenceNode.getNodeType() == Node.ELEMENT_NODE) {

                            //verificando se o nome do elemento se chama scope
                            if (flowSequenceNode.getNodeName().equals("scope")) {
                                Edge edg = readBpelScopeTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    tempArraySeq.add(edg);
                                }
                            }
                            //verificando se o nome do elemento se chama sequence
                            if (flowSequenceNode.getNodeName().equals("sequence")) {
                                Edge edg = readBpelSequenceTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    tempArraySeq.add(edg);
                                }
                            }
                        }
                    }
                    ifEdges.add(tempArraySeq);
                }

                //verificando se o nome do elemento se chama scope
                if (ifNode.getNodeName().equals("scope")) {
                    //criando um array para receber os dados das tags <scope> dentro da sequencia
                    ArrayList<Edge> tempArraySeq = new ArrayList<>();
                    //convertendo node em element para acessar mais tags.
                    Element ifSequence = (Element) ifNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList ifSequenceNodes = ifSequence.getChildNodes();
                    for (int y = 0; y < ifSequenceNodes.getLength(); y++) {
                        Node flowSequenceNode = ifSequenceNodes.item(y);
                        if (flowSequenceNode.getNodeType() == Node.ELEMENT_NODE) {

                            //verificando se o nome do elemento se chama scope
                            if (flowSequenceNode.getNodeName().equals("sequence")) {
                                Edge edg = readBpelSequenceTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    tempArraySeq.add(edg);
                                }
                            }
                        }
                    }
                    ifEdges.add(tempArraySeq);
                }

                //verificando as outras condiçoes ELSEIF e ELSE
                if (ifNode.getNodeName().equals("elseif") || ifNode.getNodeName().equals("else")) {
                    //criando um array para receber os dados das tags <scope> dentro da sequencia
                    ArrayList<Edge> tempArraySeq = new ArrayList<>();
                    //convertendo node em element para acessar mais tags.
                    Element ifSequence = (Element) ifNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList ifSequenceNodes = ifSequence.getChildNodes();
                    for (int y = 0; y < ifSequenceNodes.getLength(); y++) {
                        Node ifSequenceNode = ifSequenceNodes.item(y);
                        if (ifSequenceNode.getNodeType() == Node.ELEMENT_NODE) {
                            
                            //verificando se o nome do elemento se chama scope
                            if (ifSequenceNode.getNodeName().equals("scope")) {
                                Edge edg = readBpelScopeTag(ifSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    tempArraySeq.add(edg);
                                }
                            }

                            //verificando se o nome do elemento se chama sequence
                            if (ifSequenceNode.getNodeName().equals("sequence")) {

                                //varrer tudo que tem no sequence
                                //convertendo node em element para acessar mais tags.
                                Element ifSequenceElement = (Element) ifSequenceNode;
                                //varrendo todos os scopes dentro da colecão
                                NodeList ifSequenceElementNodes = ifSequenceElement.getChildNodes();
                                for (int w = 0; w < ifSequenceElementNodes.getLength(); w++) {
                                    Node ifSequenceElementNode = ifSequenceElementNodes.item(w);
                                    if (ifSequenceElementNode.getNodeType() == Node.ELEMENT_NODE) {

                                        //verificando se o nome do elemento se chama scope
                                        if (ifSequenceElementNode.getNodeName().equals("scope")) {
                                            Edge edg = readBpelScopeTag(ifSequenceElementNode);
                                            //verificando se existe conteudo para ser adicionado
                                            if (!edg.getName().equals("dummy")) {
                                                tempArraySeq.add(edg);
                                            }
                                        }
                                        //verificando se o nome do elemento se chama sequence
                                        if (ifSequenceElementNode.getNodeName().equals("sequence")) {
                                            Edge edg = readBpelSequenceTag(ifSequenceElementNode);
                                            //verificando se existe conteudo para ser adicionado
                                            if (!edg.getName().equals("dummy")) {
                                                tempArraySeq.add(edg);
                                            }
                                        }

                                        //HACK SAFADO CASO TENHA UM IF DENTRO DE UM IF
                                        if (ifSequenceElementNode.getNodeName().equals("if")) {
                                            //Aquie surge um problema grave, pois a minha estrututa de modelo
                                            //não permite nada fora do formato [[[A]],[[B],[C]],[[D]]]
                                            //a unica maneira de resolver esse problema seria usando STRING para armazenar a sequencia

                                            //fazendo um work arround para que a construção do modelo HYPERTIMETABLE funcione
                                            //existe um IF dentro de IF, o que acaba fugindo ao formato
                                            //mas como eu sei que nesse arquivo só existe UM operação dentro do IF
                                            Edge edg = readBpelIfTagHACK(ifSequenceElementNode);
                                            //verificando se existe conteudo para ser adicionado
                                            if (!edg.getName().equals("dummy")) {
                                                tempArraySeq.add(edg);
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                    ifEdges.add(tempArraySeq);
                }

            }
        }
        return ifEdges;
    }

    public Edge readBpelSequenceTag(Node sqNode) {

        Edge operationInfo = new Edge();
        //criando um Element para varrer tags dentro <scope>
        Element sequence = (Element) sqNode;
        //VARRENDO TODOS OS NÓS MAIS UMA VEZ PARA OBTER OS DADOS da operação executada!!!!
        //obtendo nodes do elemento
        NodeList sequenceNodes = sequence.getChildNodes();
        //varrendo todos os nós

        for (int x = 0; x < sequenceNodes.getLength(); x++) {
            Node sequenceNode = sequenceNodes.item(x);
            //verificando o tipo do elemento
            //verificando se nó é do tipo elemento
            if (sequenceNode.getNodeType() == Node.ELEMENT_NODE) //verificando se o nome do elemento se chama sequence
            {
                if (sequenceNode.getNodeName().equals("sequence")) {
                    //AQUI ACONTECE A MAGICA DA RECURSÃO
                    operationInfo = readBpelSequenceTag(sequenceNode);
                }

                if (sequenceNode.getNodeName().equals("invoke")) {
                    //criando um Element para no
                    Element invoke = (Element) sequenceNode;
                    //Obtendo informação sobre a operação
                    String operation = invoke.getAttribute("operation");

                    String partnerLink = invoke.getAttribute("partnerLink");

                    operationInfo.setName("inv_" + operation + "@" + partnerLink);
                    System.out.println("[XmlParser]" + operationInfo.getName());
                }
                if (sequenceNode.getNodeName().equals("receive")) {
                    //criando um Element para no
                    Element invoke = (Element) sequenceNode;
                    //Obtendo informação sobre a operação
                    String operation = invoke.getAttribute("operation");
                    String partnerLink = invoke.getAttribute("partnerLink");

                    operationInfo.setName("rec_" + operation + "@" + partnerLink);
                    System.out.println("[XmlParser]" + operationInfo.getName());
                }

                if (sequenceNode.getNodeName().equals("reply")) {

                    //criando um Element para no
                    Element reply = (Element) sequenceNode;
                    //Obtendo informação sobre a operação
                    String operation = reply.getAttribute("operation");
                    String partnerLink = reply.getAttribute("partnerLink");

                    operationInfo.setName("rep_" + operation + "@" + partnerLink);
                    System.out.println("[XmlParser]" + operationInfo.getName());
                }

                if (sequenceNode.getNodeName().equals("while")) {

                    //aquicomeça a magia negra, busca por recursão por sequencias ou flows
                    //carregar o nó a abrir seus filhos, varrer todos
                    //procurar por sequence ou flow
                    //CASO SEJA UM FLOW EU POSS ENVIAR MAIS DE UMA EDGE COMO RETORNO O QUE É UMA MERDA
                    //ISSO VAI QUEBRAR TODOS O SISTEMA DE GERAÇÃO DE MODELO
                }

                if (sequenceNode.getNodeName().equals("scope")) {
                    //MAGIA DA RECURSAO
                    operationInfo = readBpelScopeTag(sequenceNode);
                    System.out.println("[XmlParser]" + operationInfo.getName());
                }
            }

        }

        if (operationInfo.getName() == null || operationInfo.getName().equals("")) {
            operationInfo.setName("dummy");
        }
        return operationInfo;

    }

    public ArrayList<ArrayList<Edge>> readBpelFlowTag(Node sqNode) {

        //array que recebera as sesenquancias de operações listadas dentro do arquivo BPEL
        ArrayList<ArrayList<Edge>> flowEdges = new ArrayList<>();
        //varrendo as sequencias dentro do flow, cada sequencia é representada por um array
        //criando um Element para varrer tags dentro <scope>
        Element flow = (Element) sqNode;
        //obtendo nodes do elemento
        NodeList flowNodes = flow.getChildNodes();
        //varrendo todos os nós
        for (int x = 0; x < flowNodes.getLength(); x++) {
            Node flowNode = flowNodes.item(x);
            //verificando o tipo do elemento
            //verificando se nó é do tipo elemento
            if (flowNode.getNodeType() == Node.ELEMENT_NODE) {
                //verificando se o nome do elemento se chama sequence
                if (flowNode.getNodeName().equals("sequence")) {
                    //criando um array para receber os dados das tags <scope> dentro da sequencia
                    ArrayList<Edge> tempArraySeq = new ArrayList<>();
                    //convertendo node em element para acessar mais tags.
                    Element flowSequence = (Element) flowNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList flowSequenceNodes = flowSequence.getChildNodes();
                    for (int y = 0; y < flowSequenceNodes.getLength(); y++) {
                        Node flowSequenceNode = flowSequenceNodes.item(y);
                        if (flowSequenceNode.getNodeType() == Node.ELEMENT_NODE) {
                            //verificando se o nome do elemento se chama scope
                            if (flowSequenceNode.getNodeName().equals("scope")) {
                                Edge edg = readBpelScopeTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    tempArraySeq.add(edg);
                                }
                            }
                        }
                    }
                    flowEdges.add(tempArraySeq);
                }
            }
        }
        return flowEdges;
    }

    public Edge readBpelIfTagHACK(Node sqNode) {

        Edge operationInfo = new Edge();
        //varrendo as sequencias dentro do flow, cada sequencia é representada por um array
        //criando um Element para varrer tags dentro <scope>
        Element ifElement = (Element) sqNode;
        //obtendo nodes do elemento
        NodeList ifNodes = ifElement.getChildNodes();
        //varrendo todos os nós

        for (int x = 0; x < ifNodes.getLength(); x++) {
            Node ifNode = ifNodes.item(x);
            //verificando o tipo do elemento
            //verificando se nó é do tipo elemento
            if (ifNode.getNodeType() == Node.ELEMENT_NODE) {

                //verificando se o nome do elemento se chama sequence
                if (ifNode.getNodeName().equals("sequence")) {
                    //convertendo node em element para acessar mais tags.
                    Element ifSequence = (Element) ifNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList ifSequenceNodes = ifSequence.getChildNodes();
                    for (int y = 0; y < ifSequenceNodes.getLength(); y++) {
                        Node flowSequenceNode = ifSequenceNodes.item(y);
                        if (flowSequenceNode.getNodeType() == Node.ELEMENT_NODE) {

                            //verificando se o nome do elemento se chama scope
                            if (flowSequenceNode.getNodeName().equals("scope")) {
                                Edge edg = readBpelScopeTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    operationInfo = edg;
                                }
                            }
                            //verificando se o nome do elemento se chama sequence
                            if (flowSequenceNode.getNodeName().equals("sequence")) {
                                Edge edg = readBpelSequenceTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    operationInfo = edg;
                                }
                            }
                        }
                    }
                }

                //verificando se o nome do elemento se chama scope
                if (ifNode.getNodeName().equals("scope")) {
                    //convertendo node em element para acessar mais tags.
                    Element ifSequence = (Element) ifNode;
                    //varrendo todos os scopes dentro da colecão
                    NodeList ifSequenceNodes = ifSequence.getChildNodes();
                    for (int y = 0; y < ifSequenceNodes.getLength(); y++) {
                        Node flowSequenceNode = ifSequenceNodes.item(y);
                        if (flowSequenceNode.getNodeType() == Node.ELEMENT_NODE) {

                            //verificando se o nome do elemento se chama scope
                            if (flowSequenceNode.getNodeName().equals("sequence")) {
                                Edge edg = readBpelSequenceTag(flowSequenceNode);
                                //verificando se existe conteudo para ser adicionado
                                if (!edg.getName().equals("dummy")) {
                                    operationInfo = edg;
                                }
                            }
                        }
                    }

                }
            }
        }
        if (operationInfo.getName() == null || operationInfo.getName().equals("")) {
            operationInfo.setName("dummy");
        }
        System.out.println("IF HACK: " + operationInfo.getName());

        return operationInfo;
    }

}
