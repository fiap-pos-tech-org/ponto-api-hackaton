//package br.com.fiap.hackaton.timekeepingapi.service;
//
//import br.com.fiap.hackaton.timekeepingapi.core.dtos.MensagemDTOBase;
//import br.com.fiap.hackaton.timekeepingapi.core.ports.in.cobranca.PublicaPagamentoRetornoInputPort;
//import br.com.fiap.hackaton.timekeepingapi.core.ports.out.cobranca.PublicaPagamentoRetornoOutputPort;
//
//public class PublicaPagamentoRetornoUseCase implements PublicaPagamentoRetornoInputPort {
//
//    PublicaPagamentoRetornoOutputPort publicaPagamentoRetornoOutputPort;
//
//    public PublicaPagamentoRetornoUseCase(PublicaPagamentoRetornoOutputPort publicaPagamentoRetornoOutputPort) {
//        this.publicaPagamentoRetornoOutputPort = publicaPagamentoRetornoOutputPort;
//    }
//
//    @Override
//    public void publicar(MensagemDTOBase mensagem, String topicoArn) {
//        publicaPagamentoRetornoOutputPort.publicar(mensagem, topicoArn);
//    }
//
//}
