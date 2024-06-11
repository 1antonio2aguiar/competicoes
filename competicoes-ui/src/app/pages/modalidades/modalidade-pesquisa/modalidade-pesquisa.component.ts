import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { ConfirmationService, MessageService } from 'primeng/api';

import { ModalidadesService } from '../modalidades.service';
import { ModalidadesFiltro } from '../modalidades-filtro';
import { Modalidade } from '../../../shared/models/modalidade';
import { BaseResourceListComponent } from '../../../shared/components/base-resource-list/base-resource-list.component';


@Component({
  selector: 'ngx-modalidade-pesquisa',
  templateUrl: './modalidade-pesquisa.component.html',
  styleUrls: ['./modalidade-pesquisa.component.scss'],
})
export class ModalidadePesquisaComponent extends BaseResourceListComponent<Modalidade> {
  filtro = new ModalidadesFiltro();
  resources = [];
  loading = true;

  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      nome: {
        title: 'Nome',
        type: 'string',
      },
      descricao: {
        title: 'Descrição',
        type: 'string',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(
    private modalidadesService: ModalidadesService,
    public confirmationService: ConfirmationService,
    public messageService: MessageService
  ) {
    super(modalidadesService, confirmationService, messageService); // Passa a instância de ModalidadesService
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.modalidadesService.pesquisar(this.filtro)
    .then(resultado => {
       console.table(resultado);
       this.loading = false;
       this.filtro.totalRegistros = resultado.total;
       this.resources = resultado.estados;
    })
    .catch(erro => {
       erro = 'Erro';
       this.loading = false;
    });
  }

  /*constructor(private service: ModalidadeData) {
    const data = this.service.getData();
    this.source.load(data);
  }*/

  onDeleteConfirm(event): void {
    if (window.confirm('Voce deseja deletar este item?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }
}
