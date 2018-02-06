"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var proxy_service_1 = require('./proxy.service');
var DocumentsComponent = (function () {
    function DocumentsComponent(proxyService) {
        this.proxyService = proxyService;
    }
    DocumentsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.proxyService.getDocuments()
            .then(function (documents) { return _this.documents = documents; });
        console.log(this.documents);
    };
    DocumentsComponent = __decorate([
        core_1.Component({
            selector: 'my-documents',
            templateUrl: 'app/documents.component.html'
        }), 
        __metadata('design:paramtypes', [proxy_service_1.ProxyService])
    ], DocumentsComponent);
    return DocumentsComponent;
}());
exports.DocumentsComponent = DocumentsComponent;
//# sourceMappingURL=documents.component.js.map