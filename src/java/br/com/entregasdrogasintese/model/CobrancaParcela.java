package br.com.entregasdrogasintese.model;

public class CobrancaParcela {
    private Integer cobrancaparcelaido;
    private Double valor_pagamento_parcela;
    private Cobranca cobranca;

    public CobrancaParcela() {
    }

    public CobrancaParcela(Integer cobrancaparcelaido, Double valor_pagamento_parcela, Cobranca cobranca) {
        this.cobrancaparcelaido = cobrancaparcelaido;
        this.valor_pagamento_parcela = valor_pagamento_parcela;
        this.cobranca = cobranca;
    }

    /**
     * @return the cobrancaparcelaido
     */
    public Integer getCobrancaparcelaido() {
        return cobrancaparcelaido;
    }

    /**
     * @param cobrancaparcelaido the cobrancaparcelaido to set
     */
    public void setCobrancaparcelaido(Integer cobrancaparcelaido) {
        this.cobrancaparcelaido = cobrancaparcelaido;
    }

    /**
     * @return the valor_pagamento_parcela
     */
    public Double getValor_pagamento_parcela() {
        return valor_pagamento_parcela;
    }

    /**
     * @param valor_pagamento_parcela the valor_pagamento_parcela to set
     */
    public void setValor_pagamento_parcela(Double valor_pagamento_parcela) {
        this.valor_pagamento_parcela = valor_pagamento_parcela;
    }

    /**
     * @return the cobranca
     */
    public Cobranca getCobranca() {
        return cobranca;
    }

    /**
     * @param cobranca the cobranca to set
     */
    public void setCobranca(Cobranca cobranca) {
        this.cobranca = cobranca;
    }

    
}
