import {Component, OnInit} from '@angular/core';
import {Issue} from "../../_models/issue";
import {ToastrService} from "ngx-toastr";
import {IssueService} from "../../_services/issue.service";
import {ActivatedRoute} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {faBug, faLink} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-issue-list',
  templateUrl: './issue-list.component.html',
  styleUrl: './issue-list.component.css'
})
export class IssueListComponent implements OnInit {

  issues: Issue[] = [];
  loading = false;
  faLink = faLink;

  constructor(private issueService: IssueService,
              private toastr: ToastrService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.list(params["id"])
    })
  }

  list(repositoryId: number) {
    this.loading = true;
    this.issueService.list(repositoryId)
      .subscribe(resp => {
        this.loading = false;
        this.issues = resp;
      }, error => {
        this.toastr.error(error, "Error");
        this.loading = false;
      })
  }


  protected readonly faBug = faBug;
}
