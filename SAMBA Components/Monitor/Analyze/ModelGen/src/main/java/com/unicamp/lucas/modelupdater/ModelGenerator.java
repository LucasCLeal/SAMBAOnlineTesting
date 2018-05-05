/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import com.unicamp.lucas.common.pojo.Edge;
import com.unicamp.lucas.common.pojo.Vertex;
import com.unicamp.lucas.common.pojo.Model;
import com.unicamp.lucas.common.pojo.ModelData;

import com.unicamp.lucas.common.FileHandler;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author LucasCLeal Classe responsável por gerar um arquivo json com um modelo
 * de um processo BPEL
 */
public class ModelGenerator {

    //configuracoes para criacao de arquivos de relatorio
    static String mdExt = ".json";
    static String separator = System.getProperty("line.separator");
    static Charset charset = Charset.forName("US-ASCII");

    static Model model;
    static FileHandler flHand = new FileHandler();
    static Calendar caldt;

    public void createMoldelForBpelFile(String bpelFileName, ArrayList<ArrayList<ArrayList<Edge>>> bpelSequence) {

        model = null;
        initModelGeneration();
        startModelCreation(bpelFileName);
        //adicionando arestas
        extractEdgesFromBpelSequence(bpelSequence);
        //adicionando vertices
        extracVerticesFromBpelSequence(bpelSequence);
        //atualizando arestas com vertices
        linkVerticesAndEdgesOfModel(bpelSequence);

    }

    public void initModelGeneration() {
        //inicializando variável da classe
        //limpando qualquar informação anterior armazenada na variável
        model = new Model();
        flHand.loadFileHandlerPaths();
    }

    public void startModelCreation(String bpelFileName) {

        //cria modelo json base para implmentação de um novo modelo
        //modelo basico conta com as sequintes carecteristicas
        //nome do do arquivo BPEL que deu origem ao modelo
        //data de criação do modelo
        //modelo
        //modelo --> vertice 0
        //modelo --> aresta e_init (target v0)
        //modelo --> aresta e_loop (target v0)
        ModelData mdt = new ModelData();
        //setando informações sobre o modelo
        mdt.setName(bpelFileName);
        mdt.setId("");
        mdt.setGenerator("random(edge_coverage(100))");
        mdt.setStartElementId("e0"); //e_init edge

        //criando aresta inicial
        Edge init = new Edge();
        init.setId("e0");
        init.setName("e_init");
        init.setSourceVertexId("");
        init.setTargetVertexId("n0");

        //criando vertice inicial
        Vertex origin = new Vertex();
        origin.setId("n0");
        origin.setName("v_0");

        //armazendo vertices e arestas ao modelo
        List<Edge> edges = mdt.getEdges();
        List<Vertex> vertices = mdt.getVertices();

        edges.add(init);
        vertices.add(origin);

        mdt.setEdges(edges);
        mdt.setVertices(vertices);

        List<ModelData> models = new ArrayList<>();
        models.add(mdt);
        model.setModels(models);

    }

    private void extractEdgesFromBpelSequence(ArrayList<ArrayList<ArrayList<Edge>>> bpelSequence) {
        //lista de array será quebrada em arestas que serão adicionados de acordo com a necessidade no modelo
        //bpelSeq = [[[sq0]],[[sq1,sq2],[sq3,sq4,sq5]],[[sq6]],[[sq7]]]
        //a arraylist anterior é um exemplo. que pode ser interpretado assim
        // bpelSeq = [A(1),B(2),C(1),D(1)], sendo B(2) o unico arraylist que contem duas listas

        //iniciando o contador de arestas
        int edgeCounter = 1;
        //Para converter a squencia em um objeto edges é necessário fazer um loop e percorrer todos os Arrays
        for (int i = 0; i < bpelSequence.size(); i++) {
            //obtendo sequencias do indice atual
            ArrayList<ArrayList<Edge>> sequence = bpelSequence.get(i);
            //Varrendo elementos das sequencias.
            for (ArrayList<Edge> ArrayEdge : sequence) {
                for (Edge edg : ArrayEdge) {
                    //incrementando contador de arestas
                    addEdgeToModel(edg, edgeCounter);
                    edgeCounter++;
                }
            }
        }
        addLoopEdgeToModel(edgeCounter);
    }

    private void extracVerticesFromBpelSequence(ArrayList<ArrayList<ArrayList<Edge>>> bpelSequence) {

        //percorrer novamente os os elementos das sequencias armazenadas
        //fazer contagem de vertices existentes na sequencia
        //iniciando contador de vertices
        int vertexCounter = 1;
        System.out.println("[ModelGen] bpelSequence listed: " + Integer.toString(bpelSequence.size()));
        for (int i = 0; i < bpelSequence.size(); i++) {
            //obtendo sequencias do indice atual
            ArrayList<ArrayList<Edge>> sequence = bpelSequence.get(i);
            if (sequence.size() == 1) {
                //caso sequencia seja simples (só possuir um array)
                vertexCounter++;
            } else {
                //caso a sequencia seja complexa (possuir mais de um array)
                //para contar o numero de vertices que derivam existir em uma sequencia complexa
                //cada aresta equivale a um novo vertice
                //as unicas arestas que não adicionam vertice são as ultimas de cada sequencia
                //todos as arestas de uma sequencia complexa convergem para um vertice unico
                //que é adicionado ao final do loop

                for (ArrayList<Edge> flowSeq : sequence) {
                    for (int x = 0; x < flowSeq.size(); x++) {
                        if (x < flowSeq.size() - 1) {
                            vertexCounter++;
                        }
                    }
                }
                //adicionando o vertice que a sequencia converge
                vertexCounter++;
            }
        }
        System.out.println("[ModelGen] vertexCounter: " + Integer.toString(vertexCounter));
        addVerticesToModel(vertexCounter);

    }

    private void addEdgeToModel(Edge edg, int edgeCounter) {
        //obtendo modelData do modelo da classe
        List<ModelData> models = model.getModels();
        for (ModelData mdt : models) {
            //configurando dados de ID e NAME da aresta a ser adicionada
            edg.setId("e" + Integer.toString(edgeCounter));
            //obtendo informação sobre nome da aresta
            if (edg.getName().startsWith("inv_")) {
                //arestas originadas de tags <invoke> são mantidas
                String name = edg.getName();
                name = name.replace("inv_", "");
                edg.setName(name);
            } else if (edg.getName().startsWith("rec_")) {
                //arestas originadas de tags <recieve> são substituidas por e_edg.name
                String name = edg.getName();
                name = name.replace("rec_", "e_");
                edg.setName(name);
            } else if (edg.getName().startsWith("rep_")) {
                String name = edg.getName();
                name = name.replace("rep_", "e_");
                edg.setName(name);
            }

            //adicionando aresta ao modelData
            List<Edge> mdtEdges = mdt.getEdges();
            mdtEdges.add(edg);
            mdt.setEdges(mdtEdges);

            System.out.println("[ModelGen] new edge: " + edg.getName() + " id: " + edg.getId());
            //atualizando modelData do modelo da classe
            List<ModelData> modelsUpdate = new ArrayList<>();
            modelsUpdate.add(mdt);
            model.setModels(modelsUpdate);
        }
    }

    private void addLoopEdgeToModel(int edgeCounter) {

        List<ModelData> models = model.getModels();
        for (ModelData mdt : models) {
            //configurando dados de ID e NAME da aresta a ser adicionada
            Edge loopEdg = new Edge();

            loopEdg.setId("e" + Integer.toString(edgeCounter));
            loopEdg.setName("e_loop");

            //adicionando aresta ao modelData
            List<Edge> mdtEdges = mdt.getEdges();

            mdtEdges.add(loopEdg);
            mdt.setEdges(mdtEdges);

            //atualizando modelData do modelo da classe
            List<ModelData> modelsUpdate = new ArrayList<>();
            modelsUpdate.add(mdt);
            model.setModels(modelsUpdate);
        }
    }

    private void addVerticesToModel(int vertexCounter) {
        //adicionando o numero de vertices ao modelo
        List<ModelData> originalModels = model.getModels();
        List<ModelData> updatedModels = new ArrayList<>();

        for (ModelData mdt : originalModels) {
            //Criando nova coleção de vertices
            List<Vertex> mdlVertices = mdt.getVertices();
            for (int y = 1; y < vertexCounter; y++) {
                //como o vertice zero já existe todos os indices recebem +1
                Vertex vrt = new Vertex();
                vrt.setId("n" + Integer.toString(y));
                vrt.setName("v_" + Integer.toString(y));
                mdlVertices.add(vrt);
            }
            for (int x = 0; x < mdlVertices.size(); x++) {
                //como o vertice zero já existe todos os indices recebem +1
                Vertex vrt = mdlVertices.get(x);
                System.out.println("[ModelGen] vertice_id: " + vrt.getId() + " vertice_Name: " + vrt.getName());
            }

            mdt.setVertices(mdlVertices);
            updatedModels.add(mdt);
        }

        //atualizando modelData do modelo da classe
        model.setModels(updatedModels);
    }

    private void linkVerticesAndEdgesOfModel(ArrayList<ArrayList<ArrayList<Edge>>> bpelSequence) {

        //obtendo dados atualizados do modelo
        List<ModelData> models = model.getModels();
        //lista com modelos atualizada
        List<ModelData> updtModels = new ArrayList<>();

        //obtendo dados dos modelos
        for (ModelData mdt : models) {
            //obtendo lista de arestas do modelo
            List<Edge> edgeList = mdt.getEdges();

            //Para atualizar  é necessário fazer um loop e percorrer todas os Arrays dentro do bpelSequence
            //guardar o index de cada elemento de sequencia percorrido, pois esse index que permitirá encontrar
            //a aresta adequada para a edição dentro da lista de arestas do modelo
            int edgeIndex = 0;
            int vertexIndex = 0;
            for (int i = 0; i < bpelSequence.size(); i++) {
                //obtendo sequencias do indice atual
                ArrayList<ArrayList<Edge>> sequence = bpelSequence.get(i);
                //cado a sequencia seja simples e o indice <= edgeList.size()
                //caso a sequencia seja simples e o indice > edgeList.size()
                //caso a sequencia seja complexa e o indice > edgeList.size()
                //caso a sequencia seja complexa e o indice <= edgeList.size()

                if (sequence.size() == 1) {
                    //sequencia simples
                    if (edgeIndex < edgeList.size() - 2) {
                        //indice menor que o numero de elementos na lista de edges do modelo subtraido 2
                        //as arestas e_init e e_loop não são existentes na coleção original
                        edgeIndex++;
                        vertexIndex++;
                        edgeList = updateEdgeOnList(edgeList, edgeIndex, edgeIndex - 1, vertexIndex);
                    }

                } else {
                    //sequencia complexa
                    //guardando o index da ultima aresta editada
                    int prevIndex = edgeIndex;
                    int endIndex = 0;

                    for (int y = 0; y < sequence.size(); y++) {
                        ArrayList<Edge> flowSeq = sequence.get(y);
                        for (int x = 0; x < flowSeq.size(); x++) {

                            if (x == 0) {
                                //caso seja o primeiro elemento
                                //entretanto ele pode ser o unico elemento da sequência
                                //primeiro elemento da sequencia sai do targetVertex do prevIndex EDGE
                                edgeIndex++;
                                vertexIndex++;
                                edgeList = updateEdgeOnList(edgeList, edgeIndex, prevIndex, vertexIndex);
                               
                                if (flowSeq.size() == 1) {
                                    //caso seja o unico elemento da sequencia
                                    endIndex = edgeIndex;
                                }

                            } else if (x < flowSeq.size() - 1) {
                                //demais elementos, com exceção do ultimo
                                //apontam para os elementos anteriores como na regra normal
                                edgeIndex++;
                                vertexIndex++;
                                edgeList = updateEdgeOnList(edgeList, edgeIndex, edgeIndex - 1, vertexIndex);
                            } else if (y == 0) {
                                //caso seja o ultimo edge da primeira sequencia
                                edgeIndex++;
                                vertexIndex++;
                                edgeList = updateEdgeOnList(edgeList, edgeIndex, edgeIndex - 1, vertexIndex);
                                endIndex = edgeIndex;
                            } else {
                                //ultimos elemento das outras sequencias.
                                edgeIndex++;
                                edgeList = updateEdgeOnList(edgeList, edgeIndex, edgeIndex - 1, endIndex);
                            }
                        }
                    }

                }
            }

            //atualizando a ultima aresta e_loop pertencente ao Array de edges do modelo.
            edgeIndex++;
            edgeList = updateEdgeOnList(edgeList, edgeIndex, edgeIndex - 1, 0);

            //printando resultado
            for (Edge edg : edgeList) {
                System.out.println("[ModelGen] Edge id: " + edg.getId() + " name: " + edg.getName() + " orig: " + edg.getSourceVertexId() + " targ: " + edg.getTargetVertexId());
            }

            mdt.setEdges(edgeList);
            updtModels.add(mdt);
        }
        //atualizando variável de modelo da classe. 
        model.setModels(updtModels);

    }

    private List<Edge> updateEdgeOnList(List<Edge> edgeList, int edgeIndex, int preEdgeIndex, int targetVerIndex) {

        Edge edg = edgeList.get(edgeIndex);
        Edge prevEdg = edgeList.get(preEdgeIndex);

        edg.setSourceVertexId(prevEdg.getTargetVertexId());
        edg.setTargetVertexId("n" + Integer.toString(targetVerIndex));
        //atualizando edgeList

        System.out.println("updateEdgeList: " + edg.toString());

        edgeList.set(edgeIndex, edg);

        return edgeList;
    }

    public String savingModelToTargertDir(String bpelFileName, Path targetPath) throws IOException {

        //criando arquivo no local de destino
        Path mdPath = flHand.generateFilePath(targetPath, bpelFileName + mdExt);
        flHand.createNewFileAtPath(mdPath);

        model.setName(bpelFileName);
        //criando objeto json para modelo
        //escreve conteúdo no arquivo do modelo
        flHand.writeJsonInToFileAtPath(mdPath, model);

        System.out.println("[ModelGen] model name: " + model.getName());

        return model.toString();
    }

}
