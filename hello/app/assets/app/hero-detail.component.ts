import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Hero }        from './hero';
import { HeroService } from './hero.service';

@Component({
//  moduleId: module.id,
  selector: 'my-hero-detail',
  templateUrl: 'assets/app/hero-detail.component.html',
  styleUrls: [ 'assets/app/hero-detail.component.css' ],
})
export class HeroDetailComponent implements OnInit {
  public hero: Hero;

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute,
    private location: Location,
  ) {}

  public ngOnInit(): void {
    this.route.params // tslint:disable-next-line:no-string-literal
      .switchMap((params: Params) => this.heroService.getHero(+params['id']))
      .subscribe(hero => this.hero = hero);
  }

  public save(): void {
    this.heroService.update(this.hero)
      .then(() => this.goBack());
  }

  public goBack(): void {
    this.location.back();
  }
}

/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
