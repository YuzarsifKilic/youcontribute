import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RepositoryService} from "../services/repository.service";
import {first} from "rxjs";
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrl: './import.component.css'
})
export class ImportComponent {

  faPlus = faPlus
  loading = false;

  importRepositoryForm: FormGroup = this.formBuilder.group({
    organization: ['', Validators.required],
    repository: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder,
              private repositoryService: RepositoryService,
              private toastr: ToastrService) {}

  ngOnInit(): void {

  }

  import() {
    this.loading = true;
    this.repositoryService.import(this.importRepositoryForm.get("organization")?.value, this.importRepositoryForm.get("repository")?.value)
      .pipe(first())
      .subscribe(() => {
          this.loading = false;
          this.toastr.success("Imported successfully", "Success")
        },
        error => {
          this.loading = false;
          this.toastr.error(error.error.message, "Error")
        })
  }
}
