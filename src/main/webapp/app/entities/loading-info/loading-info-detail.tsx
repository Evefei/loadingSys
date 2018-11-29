import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loading-info.reducer';
import { ILoadingInfo } from 'app/shared/model/loading-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoadingInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoadingInfoDetail extends React.Component<ILoadingInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loadingInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="loadingSysApp.loadingInfo.detail.title">LoadingInfo</Translate> [<b>{loadingInfoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="step">
                <Translate contentKey="loadingSysApp.loadingInfo.step">Step</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.step}</dd>
            <dt>
              <span id="materialCode">
                <Translate contentKey="loadingSysApp.loadingInfo.materialCode">Material Code</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.materialCode}</dd>
            <dt>
              <span id="colorCode">
                <Translate contentKey="loadingSysApp.loadingInfo.colorCode">Color Code</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.colorCode}</dd>
            <dt>
              <span id="layoutType">
                <Translate contentKey="loadingSysApp.loadingInfo.layoutType">Layout Type</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.layoutType}</dd>
            <dt>
              <span id="quantity">
                <Translate contentKey="loadingSysApp.loadingInfo.quantity">Quantity</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.quantity}</dd>
            <dt>
              <span id="row">
                <Translate contentKey="loadingSysApp.loadingInfo.row">Row</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.row}</dd>
            <dt>
              <span id="column">
                <Translate contentKey="loadingSysApp.loadingInfo.column">Column</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.column}</dd>
            <dt>
              <span id="layer">
                <Translate contentKey="loadingSysApp.loadingInfo.layer">Layer</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.layer}</dd>
            <dt>
              <span id="lengthPosition">
                <Translate contentKey="loadingSysApp.loadingInfo.lengthPosition">Length Position</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.lengthPosition}</dd>
            <dt>
              <span id="widthPosition">
                <Translate contentKey="loadingSysApp.loadingInfo.widthPosition">Width Position</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.widthPosition}</dd>
            <dt>
              <span id="heightPosition">
                <Translate contentKey="loadingSysApp.loadingInfo.heightPosition">Height Position</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.heightPosition}</dd>
            <dt>
              <span id="length">
                <Translate contentKey="loadingSysApp.loadingInfo.length">Length</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.length}</dd>
            <dt>
              <span id="width">
                <Translate contentKey="loadingSysApp.loadingInfo.width">Width</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.width}</dd>
            <dt>
              <span id="height">
                <Translate contentKey="loadingSysApp.loadingInfo.height">Height</Translate>
              </span>
            </dt>
            <dd>{loadingInfoEntity.height}</dd>
            <dt>
              <Translate contentKey="loadingSysApp.loadingInfo.loadingId">Loading Id</Translate>
            </dt>
            <dd>{loadingInfoEntity.loadingId ? loadingInfoEntity.loadingId.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/loading-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/loading-info/${loadingInfoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ loadingInfo }: IRootState) => ({
  loadingInfoEntity: loadingInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoadingInfoDetail);
