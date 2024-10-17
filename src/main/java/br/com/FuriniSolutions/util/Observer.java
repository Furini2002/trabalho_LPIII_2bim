package br.com.FuriniSolutions.util;

import br.com.FuriniSolutions.bean.NotaFiscal;

/**
 *
 * @author lucas
 */
public interface Observer {
    
    void onNotaFiscalAdded(NotaFiscal nota);
    
}
