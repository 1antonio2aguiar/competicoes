import { Injectable, Injector } from '@angular/core';

import { environment } from '../../../environments/environment';
import { BaseResourceService } from '../../shared/services/base-resource.service';
import { Modalidade } from '../../shared/models/modalidade';
import { ModalidadesFiltro } from './modalidades-filtro';


@Injectable({
  providedIn: 'root'
})

export class ModalidadesService /*extends BaseResourceService<Modalidade>*/{

  /*constructor(protected injector: Injector) {
    super(environment.apiUrl + 'tipoModalidade', injector, Modalidade.fromJson);
  }*/

  /*pesquisar(filtro: ModalidadesFiltro): Promise<any> {
    let params = filtro.params;
    params = params
      .append('page', filtro.pagina.toString())
      .append('size', filtro.itensPorPagina.toString());
    return this.http
      .get<any>(this.apiPath, { params })
      .toPromise()
      .then((response) => {
        const estados = response.content;
        const resultado = {
          estados,
          total: response.totalElements,
        };
        return resultado;
      });
  }*/

  /*listAll(): Promise<any> {
    return this.http
      .get<any>(this.apiPath + '/list')
      .toPromise()
      .then((response) => response);
  }*/

}
