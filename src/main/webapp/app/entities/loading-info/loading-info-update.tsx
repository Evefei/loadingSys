import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILoading } from 'app/shared/model/loading.model';
import { getEntities as getLoadings } from 'app/entities/loading/loading.reducer';
import { getEntity, updateEntity, createEntity, reset } from './loading-info.reducer';
import { ILoadingInfo } from 'app/shared/model/loading-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoadingInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoadingInfoUpdateState {
  isNew: boolean;
  loadingIdId: string;
}

export class LoadingInfoUpdate extends React.Component<ILoadingInfoUpdateProps, ILoadingInfoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      loadingIdId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getLoadings();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { loadingInfoEntity } = this.props;
      const entity = {
        ...loadingInfoEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/loading-info');
  };

  render() {
    const { loadingInfoEntity, loadings, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="loadingSysApp.loadingInfo.home.createOrEditLabel">
              <Translate contentKey="loadingSysApp.loadingInfo.home.createOrEditLabel">Create or edit a LoadingInfo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loadingInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loading-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="stepLabel" for="step">
                    <Translate contentKey="loadingSysApp.loadingInfo.step">Step</Translate>
                  </Label>
                  <AvField id="loading-info-step" type="string" className="form-control" name="step" />
                </AvGroup>
                <AvGroup>
                  <Label id="materialCodeLabel" for="materialCode">
                    <Translate contentKey="loadingSysApp.loadingInfo.materialCode">Material Code</Translate>
                  </Label>
                  <AvField id="loading-info-materialCode" type="text" name="materialCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="colorCodeLabel" for="colorCode">
                    <Translate contentKey="loadingSysApp.loadingInfo.colorCode">Color Code</Translate>
                  </Label>
                  <AvField id="loading-info-colorCode" type="text" name="colorCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="layoutTypeLabel" for="layoutType">
                    <Translate contentKey="loadingSysApp.loadingInfo.layoutType">Layout Type</Translate>
                  </Label>
                  <AvField id="loading-info-layoutType" type="text" name="layoutType" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="quantity">
                    <Translate contentKey="loadingSysApp.loadingInfo.quantity">Quantity</Translate>
                  </Label>
                  <AvField id="loading-info-quantity" type="string" className="form-control" name="quantity" />
                </AvGroup>
                <AvGroup>
                  <Label id="rowLabel" for="row">
                    <Translate contentKey="loadingSysApp.loadingInfo.row">Row</Translate>
                  </Label>
                  <AvField id="loading-info-row" type="string" className="form-control" name="row" />
                </AvGroup>
                <AvGroup>
                  <Label id="columnLabel" for="column">
                    <Translate contentKey="loadingSysApp.loadingInfo.column">Column</Translate>
                  </Label>
                  <AvField id="loading-info-column" type="string" className="form-control" name="column" />
                </AvGroup>
                <AvGroup>
                  <Label id="layerLabel" for="layer">
                    <Translate contentKey="loadingSysApp.loadingInfo.layer">Layer</Translate>
                  </Label>
                  <AvField id="loading-info-layer" type="string" className="form-control" name="layer" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthPositionLabel" for="lengthPosition">
                    <Translate contentKey="loadingSysApp.loadingInfo.lengthPosition">Length Position</Translate>
                  </Label>
                  <AvField id="loading-info-lengthPosition" type="text" name="lengthPosition" />
                </AvGroup>
                <AvGroup>
                  <Label id="widthPositionLabel" for="widthPosition">
                    <Translate contentKey="loadingSysApp.loadingInfo.widthPosition">Width Position</Translate>
                  </Label>
                  <AvField id="loading-info-widthPosition" type="text" name="widthPosition" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightPositionLabel" for="heightPosition">
                    <Translate contentKey="loadingSysApp.loadingInfo.heightPosition">Height Position</Translate>
                  </Label>
                  <AvField id="loading-info-heightPosition" type="text" name="heightPosition" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="length">
                    <Translate contentKey="loadingSysApp.loadingInfo.length">Length</Translate>
                  </Label>
                  <AvField id="loading-info-length" type="text" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="widthLabel" for="width">
                    <Translate contentKey="loadingSysApp.loadingInfo.width">Width</Translate>
                  </Label>
                  <AvField id="loading-info-width" type="text" name="width" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightLabel" for="height">
                    <Translate contentKey="loadingSysApp.loadingInfo.height">Height</Translate>
                  </Label>
                  <AvField id="loading-info-height" type="text" name="height" />
                </AvGroup>
                <AvGroup>
                  <Label for="loadingId.id">
                    <Translate contentKey="loadingSysApp.loadingInfo.loadingId">Loading Id</Translate>
                  </Label>
                  <AvInput id="loading-info-loadingId" type="select" className="form-control" name="loadingId.id">
                    <option value="" key="0" />
                    {loadings
                      ? loadings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loading-info" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  loadings: storeState.loading.entities,
  loadingInfoEntity: storeState.loadingInfo.entity,
  loading: storeState.loadingInfo.loading,
  updating: storeState.loadingInfo.updating,
  updateSuccess: storeState.loadingInfo.updateSuccess
});

const mapDispatchToProps = {
  getLoadings,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoadingInfoUpdate);
